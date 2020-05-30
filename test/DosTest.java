import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.senjoeson.cordovahelp.dos.DosUtils;

/**
 * @author MyPC
 * @date 2018/7/20
 */

public class DosTest {


    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("cordova.cmd");
        //strings.add("node");
        strings.add("-v");
        DosUtils.runCmd(strings);


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