package main.cordova;


import java.util.ArrayList;

import main.dos.DosUtils;

/**
 * @author MyPC
 * @date 2018/7/19
 */

public class PlugmanUtils {
    /**
     * plugman create --name <pluginName> --plugin_id <pluginID> --plugin_version <version> [--path <directory>] [--variable NAME=VALUE]
     * //  plugman create --name test --plugin_id com.senjoeson.test --plugin_version 1.0.0 --variable relese
     */
    public static String create(String rootDir, String plugname, String packagename, String version) {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("plugman.cmd");
        commands.add("create");
        commands.add("--name");
        commands.add(plugname);
        commands.add("--plugin_id");
        commands.add(packagename);
        commands.add("--plugin_version");
        commands.add(version);
        commands.add("--variable");
        commands.add("release");
    return    DosUtils.runCmdByCd(rootDir, commands);
    }


    /**
     * plugman platform add --platform_name <platform>
     */
    public static String addPlatform(String plugPath, String platform) {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("plugman.cmd");
        commands.add("platform");
        commands.add("add");
        commands.add("--platform_name");
        commands.add(platform);
        return   DosUtils.runCmdByCd(plugPath, commands);

    }

    /**
     * plugman platform remove --platform_name <platform>
     */
    public static void rmPlatform(String plugPath, String platform) {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("plugman.cmd");
        commands.add("platform");
        commands.add("remove");
        commands.add("--platform_name");
        commands.add(platform);
        DosUtils.runCmdByCd(plugPath, commands);
    }

    /**
     * plugman createpackagejson <directory>
     * 暂时不对外提供,命令还有待完善
     */
    private static void createPackageJson(String plugPath) {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("plugman.cmd");
        commands.add("createpackagejson");
        commands.add(plugPath);
        commands.add("\n");
        commands.add("com.senjoeson.test");
        commands.add("\n");
        commands.add("1.0.0");
        commands.add("\n");
        commands.add("\n");
        commands.add("\n");
        commands.add("\n");
        commands.add(plugPath);
        DosUtils.runCmdByCd(plugPath, commands);
    }

}
