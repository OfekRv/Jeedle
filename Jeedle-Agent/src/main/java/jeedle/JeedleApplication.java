package jeedle;

import com.google.gson.Gson;
import jeedle.entities.Agent;
import jeedle.services.AgentBackgroundService;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.net.InetAddress;
import java.util.UUID;

import static jeedle.utils.HttpUtil.sendPOST;

public class JeedleApplication {
    private final static Gson gson = new Gson();

    public static void agentmain(String agentArgs, Instrumentation inst) {
        try {
            UUID uid = UUID.randomUUID();
            Agent agent = new Agent(uid.toString(),
                    InetAddress.getLocalHost().getHostAddress().toString(),
                    1);
            sendPOST(agentArgs + "/register", gson.toJson(agent));
            new AgentBackgroundService().listen(agentArgs, agent);
        } catch (IOException e) {
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) {
    }
}
