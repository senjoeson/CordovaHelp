package application.cordova;

import java.util.ArrayList;
import java.util.List;

import application.dos.DosUtils;

/**
 * @author MyPC
 * @date 2018/7/10
 * function  Cordova 命令都在这里执行,并且结果也在这里输出
 */

public class CordovaUtils {
    /**
     * 创建一个cordova项目
     *
     * @param rootPath    项目的根目录
     * @param moduleName  项目名称
     * @param packageName 包名
     */
    public static String create(String rootPath, String moduleName, String packageName) {
        ArrayList<String> commands = new ArrayList<String>();
        commands.add("cordova.cmd");
        commands.add("create");
        commands.add(rootPath + "\\" + moduleName);
        commands.add(packageName);
        return DosUtils.runCmd(commands);
    }

    /**

     * @param moduleName  项目名称
     * @param packageName 包名
     * @return 拿到执行命令
    public static List<String> createCordova(String rootPath, String moduleName, String packageName) {
    ArrayList<String> commands = new ArrayList<String>();
    commands.add("C:\\Users\\MyPC\\AppData\\Roaming\\npm\\node_modules\\cordova\\bin\\cordova.cmd");
    commands.add("create");
    commands.add(rootPath + "\\" + moduleName);
    commands.add(packageName);
    return commands;
    }*/


    /**
     * 添加平台
     *
     * @param modulePath   设置在当前的目录下
     * @param platformName 平台名称 android ios
     */
    public static String addPlatform(String modulePath, String platformName) {
        //切换项目路径，然后执行添加平台操作
        List<String> commands = new ArrayList<String>();
        commands.add("cordova.cmd");
        commands.add("platform");
        commands.add("add");
        commands.add(platformName);
        String runCmd = DosUtils.runCmdByCd(modulePath, commands);
        System.out.println(runCmd);
        return runCmd;

    }

    /**
     * @param modulePath 项目的路径
     * @param platform   平台名称,目前仅支持android,ios
     * @return
     */
    public static String runAndroid(String modulePath, String platform) {
        List<String> commands = new ArrayList<String>();
        commands.add("cordova.cmd");
        commands.add("run");
        commands.add(platform);
        return DosUtils.runCmdByCd(modulePath, commands);

    }

    /**
     * 暂时不对外提供
     *
     * @return
     */
    private static String stopAllOrder() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("pause");
        return DosUtils.runCmd(commands);
    }


    public static String showPluginList() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("cordova.cmd");
        commands.add("plugin");
        commands.add("list");
        return DosUtils.runCmd(commands);
    }


    public static String addPlugin(String projectPath, String pluginPaht) {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("cordova.cmd");
        commands.add("plugin");
        commands.add("add");
        commands.add(pluginPaht);
        return DosUtils.runCmdByCd(projectPath,commands);
    }
}
