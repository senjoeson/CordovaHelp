package application.utils;


import java.io.File;

/**
 * @author MyPC
 * @date 2018/7/18
 * @function 主要用作日志收集
 */

public class LogUtils {


    private static final boolean isLogShow = true;


    private static String LOG_FILE = System.getProperty("user.dir") + File.separator + "logFile.log";


    public static void d(String message) {
        if (isLogShow) {
            System.out.println(addErrorInfo(message));
        }
        writeLog(addErrorInfo(message) + "\n");
    }


    private static String addErrorInfo(String message) {
        String location = "";
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        location = String.format("[%s]\t[Method:%s]\t[LineNumber:%d]\t: ", stacks[2].getClassName(), stacks[2].getMethodName(), stacks[2].getLineNumber());
        return location + "  " + message;
    }


    public static void writeLog(String message) {
        File file = new File(LOG_FILE);
        WriteUtils.writeFile(file, message, file.exists() && file.length() > 0);
    }


}
