package application.dos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * @author MyPC
 * @date 2018/7/10
 */

public class DosUtils {


    private DosUtils() {
    }

    private static Process mProcess;

    /**
     * @param commands 执行dos的命令
     * @return 返回执行输出日志
     */
    public static String runCmd(List<String> commands) {
        StringBuilder result = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            Map<String, String> environment = processBuilder.environment();
            environment.putAll(System.getenv());
            processBuilder.command(commands);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line;
            mProcess = process;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
                System.out.println(processBuilder.command().toString() + " --->: " + line);
            }
            process.waitFor();
            int exit = process.exitValue();
            if (exit != 0) {
                System.out.println("failed to execute:" + processBuilder.command() + " with result:" + result);
            } else {
                return result.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    /**
     * 运行cmdm命令(需要切换目录)
     *
     * @param modulePath 要切换的目录
     * @param commands   执行的命令集合
     * @return 运行结果
     */
    public static String runCmdByCd(String modulePath, List<String> commands) {
        StringBuilder result = new StringBuilder();
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
            mProcess = process;
            readExecuteResult(result, processBuilder, process);
            int exit = process.exitValue();
            if (exit != 0) {
                System.out.println("failed to execute:" + processBuilder.command() + " with result:" + result);
            } else {
                return result.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private static void readExecuteResult(StringBuilder result, ProcessBuilder processBuilder, Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line).append("\n");
            System.out.println(processBuilder.command().toString() + " --->: " + line);
        }
    }

    /**
     * 该方法看起来没有执行
     */
    public static String stopMyOrder() {
        if (mProcess != null) {
            mProcess.destroy();
            return "已执行杀死命令！";
        } else {
            return "程序已经执行完成!";
        }
    }

}
