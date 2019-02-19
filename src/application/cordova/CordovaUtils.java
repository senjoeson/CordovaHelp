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
        List<String> commands = new ArrayList<String>();
        //  commands.add("cordova.cmd");
        commands = judgeRunPlatform(commands);
        commands.add("create");
        commands.add(rootPath + "\\" + moduleName);
        commands.add(packageName);
        return DosUtils.runCmd(commands);
    }


    /**
     * 添加平台
     *
     * @param modulePath   设置在当前的目录下
     * @param platformName 平台名称 android ios
     */
    public static String addPlatform(String modulePath, String platformName) {
        //切换项目路径，然后执行添加平台操作
        List<String> commands = new ArrayList<String>();
        //  commands.add("cordova.cmd");
        commands = judgeRunPlatform(commands);
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
        //commands.add("cordova.cmd");
        commands = judgeRunPlatform(commands);
        commands.add("run");
        commands.add(platform);
        return DosUtils.runCmdByCd(modulePath, commands);

    }


    /**
     * 显示当前Cordova项目的插件列表
     */
    public static String showPluginList(String projectPath) {
        List<String> commands = new ArrayList<>();
        //  commands.add("cordova.cmd");
        commands = judgeRunPlatform(commands);
        commands.add("plugin");
        commands.add("list");
        return DosUtils.runCmdByCd(projectPath, commands);
    }

    /**
     * 为平台添加插件
     *
     * @param projectPath 项目路径
     * @param pluginPath  插件路径
     */
    public static String addPlugin(String projectPath, String pluginPath) {
        List<String> commands = new ArrayList<>();
        //  commands.add("cordova.cmd");
        commands = judgeRunPlatform(commands);
        commands.add("plugin");
        commands.add("add");
        commands.add(pluginPath);
        return DosUtils.runCmdByCd(projectPath, commands);
    }

    /**
     * 移除插件
     *
     * @param projectPath   项目路径
     * @param pluginPackage 插件路径
     */
    public static String rmPlugin(String projectPath, String pluginPackage) {
        List<String> commands = new ArrayList<>();
        //   commands.add("cordova.cmd");
        commands = judgeRunPlatform(commands);
        commands.add("plugin");
        commands.add("remove");
        commands.add(pluginPackage);
        return DosUtils.runCmdByCd(projectPath, commands);
    }

    /**
     * 通过判断系统 调整命令
     *
     * @param commands
     * @return
     */
    public static List<String> judgeRunPlatform(List<String> commands) {
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Mac OS")) {
            // 苹果
            commands.add("cordova");
        } else if (osName.startsWith("Windows")) {
            // windows
            commands.add("cordova.cmd");
        } else {
            commands.add("cordova");

        }
        return commands;
    }


}
