import java.lang.instrument.Instrumentation;

public class JeedleApplication {
    public static void agentmain(String agentArgs, Instrumentation inst) {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) {
    }
}
