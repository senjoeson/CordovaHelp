import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author MyPC
 * @date 2018/7/20
 */

public class DosTest {


    public static void main(String[] args) {
        String modulePath = "C:\\Users\\MyPC\\IdeaProjects\\TestPlugin";
        StringBuilder result = new StringBuilder();
        ArrayList<String> commands = new ArrayList<>();
        commands.add("npm.cmd");
        commands.add("init");
        commands.add("\n");

        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            Map<String, String> environment = processBuilder.environment();
            environment.putAll(System.getenv());
            processBuilder.command(commands);
            File file = new File(modulePath);
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
            }
            processBuilder.directory(file);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            readExecuteResult(result, processBuilder, process);
            writeOrder(process);
            int exit = process.exitValue();
            if (exit != 0) {
                System.out.println("failed to execute:" + processBuilder.command() + " with result:" + result);
            } else {
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void writeOrder(Process process) throws InterruptedException, IOException {
        process.waitFor(2, TimeUnit.SECONDS);
        process.getOutputStream().write("testplugin\n".getBytes());
        process.getOutputStream().write("1.0.0\n".getBytes());
        process.getOutputStream().write("none\n".getBytes());
        process.getOutputStream().write("index.js\n".getBytes());
        process.getOutputStream().write("none\n".getBytes());
        process.getOutputStream().write("\n".getBytes());
        process.getOutputStream().write("\n".getBytes());
        process.getOutputStream().write("senjoeson\n".getBytes());
        process.getOutputStream().write("ISC\n".getBytes());
        process.getOutputStream().write("\n".getBytes());
        process.getOutputStream().write("\n".getBytes());
        process.getOutputStream().flush();
        process.getOutputStream().close();
    }

    private static void readExecuteResult(StringBuilder result, ProcessBuilder processBuilder, Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line).append("\n");
            System.out.println(processBuilder.command().toString() + " --->: " + line);
        }
    }
}