package injector;

import static utils.ResourceUtil.writeFileToLocalMachine;

import static utils.HttpUtil.downloadFileHttp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.jar.JarEntry;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class JarInjector {
	public Collection<String> getInjectableJars() {
		Collection<VirtualMachineDescriptor> vms = VirtualMachine.list();
		Collection<String> vmNames = new ArrayList<>();
		for (VirtualMachineDescriptor vm : vms) {
			vmNames.add(vm.displayName());
		}
		return vmNames;
	}

	public boolean injectAgentToJar(String jarName, String agentName) {
		Collection<VirtualMachineDescriptor> vms = VirtualMachine.list();
		for (VirtualMachineDescriptor vmd : vms) {
			if (vmd.displayName().equals(jarName)) {
				try {
					VirtualMachine jvm = VirtualMachine.attach(vmd.id());
					String agentUrl = "http://localhost:8000/downloadAgent";
					File agent = new File(agentName);
					downloadFileHttp(agentUrl, agent.getPath());
					jvm.loadAgent(agent.getPath());
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
