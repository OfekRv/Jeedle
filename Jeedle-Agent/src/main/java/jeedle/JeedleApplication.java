package jeedle;

import com.google.gson.Gson;
import jeedle.entities.Agent;
import jeedle.services.AgentBackgroundService;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.net.InetAddress;

import static jeedle.utils.HttpUtil.sendPOST;

public class JeedleApplication {
    private final static Gson gson = new Gson();

    public static void agentmain(String agentArgs, Instrumentation inst) {
        try {
            Agent agent = new Agent("2", InetAddress.getLocalHost().toString(), 1);
            sendPOST("http://localhost:8081/register", gson.toJson(agent));
            new AgentBackgroundService().listen("http://localhost:8081", agent);
        } catch (IOException e) {
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) {
    }
}
