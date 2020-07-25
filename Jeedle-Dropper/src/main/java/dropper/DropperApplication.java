package dropper;

import com.google.gson.Gson;
import dropper.entities.Agent;
import dropper.services.DropperBackgroundService;

import java.io.IOException;
import java.net.InetAddress;
import java.util.UUID;

import static dropper.utils.HttpUtil.sendPOST;

public class DropperApplication {
    private final static Gson gson = new Gson();

    public static void main(String args[]) {
        try {
            UUID uid = UUID.randomUUID();
            Agent agent = new Agent(uid.toString(),
                    InetAddress.getLocalHost().getHostAddress().toString(),
                    0);
            sendPOST(args[0] + "/register", gson.toJson(agent));
            new DropperBackgroundService().listen(args[0], agent);
        } catch (IOException e) {
        }
    }
}
