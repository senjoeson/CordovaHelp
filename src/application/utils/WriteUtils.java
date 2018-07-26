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
    //需要配置的 packagename  version  description  enter point

    /**
     * 给插件生成一个package.json
     *
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
            if(FileUtils.fileExist(fileName)){
                return true;
            }else{
                return false;
            }
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
    public static boolean writeFile(File file, String writeContent) {
        if (file != null) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(writeContent);
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

    /**
     * name : KayakDevicePlugin
     * version : 1.0.0
     * description : 设备信息
     * cordova : {"id":"com.kayak.plugin.deviceplugin","platforms":[]}
     * repository : {"type":"git","url":"git@10.5.0.182:kayakplug/KayakDevicePlugin.git"}
     * keywords : ["ecosystem:cordova"]
     * author : xmzhang
     * license : ISC
     */
    public static void main(String[] args) {
       /* String path = "C:\\Users\\senjoeson\\Desktop\\plugins\\package.json";
        PackageJson packageJson = new PackageJson();
        packageJson.setName("KayakDevicePlugin");
        packageJson.setVersion("1.0.0");
        packageJson.setDescription("com.kayak.plugin.deviceplugin");
      //  PackageJson.CordovaBean cordovaBean = new PackageJson.CordovaBean();
        cordovaBean.setId("com.kayak.plugin.deviceplugin");
        cordovaBean.setPlatforms(null);
        packageJson.setCordova(cordovaBean);
        PackageJson.RepositoryBean repositoryBean = new PackageJson.RepositoryBean();
        repositoryBean.setType("git");
        repositoryBean.setUrl("git@10.5.0.182:kayakplug/KayakDevicePlugin.git");
        packageJson.setRepository(repositoryBean);
        ArrayList<String> list = new ArrayList<>();
        list.add("ecosystem:cordova");
        packageJson.setKeywords(list);
        packageJson.setAuthor("xmzhang");
        packageJson.setLicense("ISC");

        writeFile(path, packageJson);*/


    }
}
