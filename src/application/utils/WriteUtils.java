package application.utils;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import application.bean.PackageJson;

/**
 * @author MyPC
 * @date 2018/7/25
 */

public class WriteUtils {


    /**
     * 给插件生成一个package.json
     *需要配置的 packagename  version  description  enter point
     * @param fileName    文件名
     * @param packageJson 写入内容
     */
    public static boolean writeFile(String fileName, PackageJson packageJson) {
        try {
            FileWriter fileWriter = new FileWriter(new File(fileName));
            Gson gson = new Gson();
            String json = gson.toJson(packageJson);
            fileWriter.write(json);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param file         写入的文件
     * @param writeContent 写入的内容
     * @return 执行是否成功
     * @throws IOException
     */
    public static boolean writeFile(File file, String writeContent, boolean isAppend) {
        if (file != null) {
            try {
                FileWriter fileWriter = new FileWriter(file,isAppend);
                if (isAppend) {
                    fileWriter.append(writeContent);
                } else {
                    fileWriter.write(writeContent);
                }
                fileWriter.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }


    }


}
