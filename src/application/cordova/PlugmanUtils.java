package application.cordova;


import java.io.File;
import java.util.ArrayList;

import application.bean.PackageJson;
import application.dos.DosUtils;
import application.utils.WriteUtils;

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
        return DosUtils.runCmdByCd(rootDir, commands);
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
        return DosUtils.runCmdByCd(plugPath, commands);

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
     * plugman createpackagejson
     * 由于通过DOS命令方式没有找到更好的解决方案,通过IO流写入比较靠谱
     * package.json一般用于对项目的解释
     */
    public static boolean createPackageJson(String pluginPath, String pluginName,
                                            String version, String packageName) {
        String generationFileName = pluginPath + File.separator + "package.json";
        PackageJson packageJson = new PackageJson();
        packageJson.setName(pluginName);
        packageJson.setVersion(version);
        packageJson.setDescription("none");
        packageJson.setCordova(new PackageJson.CordovaBean(packageName, null));
        packageJson.setRepository(null);
        packageJson.setLicense("ISC");
        packageJson.setAuthor("All copyRight By SENJOESON");
        return WriteUtils.writeFile(generationFileName, packageJson);
    }


}
