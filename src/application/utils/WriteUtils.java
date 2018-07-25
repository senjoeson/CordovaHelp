package application.utils;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import application.bean.PackageJson;

/**
 * @author MyPC
 * @date 2018/7/25
 */

public class WriteUtils {
    //需要配置的 packagename  version  description  enter point


    public static void writeFile(String fileName, PackageJson packageJson) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(new File(fileName));
            Gson gson = new Gson();
            String json = gson.toJson(packageJson);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
        e.printStackTrace();
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
        String path="C:\\Users\\MyPC\\IdeaProjects\\TestPluginxxx\\package.json";
        PackageJson packageJson = new PackageJson();
        packageJson.setName("KayakDevicePlugin");
        packageJson.setVersion("1.0.0");
        packageJson.setDescription("com.kayak.plugin.deviceplugin");
        PackageJson.CordovaBean cordovaBean = new PackageJson.CordovaBean();
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
        try {
            writeFile(path,packageJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
