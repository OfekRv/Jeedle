package jeedle.executors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdExecutor implements Executor<String> {
    @Override
    public String execute(String[] args) {
        StringBuilder output = new StringBuilder();
        String currLine;
        try {
            Process process = Runtime.getRuntime().exec("cmd.exe /C " + args[0]);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                while ((currLine = reader.readLine()) != null) {
                    output.append("\n" + currLine);
                }
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                while ((currLine = reader.readLine()) != null) {
                    output.append("\n" + currLine);
                }
            }
            process.waitFor();
        } catch (IOException e) {
            return e.getMessage();
        } catch (InterruptedException e) {
            return e.getMessage();
        }
        return output.toString();
    }
}
