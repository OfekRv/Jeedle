package jeedle.executors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CmdExecutor implements Executor<String> {
    @Override
    public String execute(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", args[0]);
        StringBuilder output = new StringBuilder();
        try {
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line);
                }

                int exitCode = process.waitFor();
                return output.toString();
            }
        } catch (IOException e) {
            return e.getMessage();
        } catch (InterruptedException e) {
            return e.getMessage();
        }
    }
}
