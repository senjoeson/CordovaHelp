package application.utils;

import java.io.File;

/**
 * 主要提供一些Cordova项目的固定路径写法
 */
public class PathUtils {


    public static String getSrcPath(String pluginPath, String platform) {
        return pluginPath + File.separator + "src" + File.separator + platform;
    }

}

