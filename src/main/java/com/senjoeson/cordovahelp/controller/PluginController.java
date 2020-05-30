package com.senjoeson.cordovahelp.controller;

import com.senjoeson.cordovahelp.cordova.PlugmanUtils;
import com.senjoeson.cordovahelp.utils.CustomThread;
import com.senjoeson.cordovahelp.utils.DirectoryWindowsUtils;
import com.senjoeson.cordovahelp.utils.FileUtils;
import com.senjoeson.cordovahelp.utils.LogUtils;
import com.senjoeson.cordovahelp.utils.MessageUtils;
import com.senjoeson.cordovahelp.utils.PathUtils;
import com.senjoeson.cordovahelp.utils.PreferenceUtils;
import com.senjoeson.cordovahelp.utils.TextUtils;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @author MyPC
 * @date 2018/7/19
 * function 生成插件的界面控制器
 */

public class PluginController implements Initializable {
    public ProgressIndicator mProgressIndicator;
    public TextField Tf_pluginDir;
    public TextField Tf_pluginname;
    public TextField versionname;
    public TextField packagename;
    public TextArea displayLog;
    public Button openChooseFile;

    private String pluginDir;
    private String pluginName;
    //暂时所有的平台默认是android
    private String platform;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tf_pluginDir.setText(PreferenceUtils.getString("plug_dir",""));
        Tf_pluginname.setText(PreferenceUtils.getString("plug_name",""));
        packagename.setText(PreferenceUtils.getString("plug_pkg_name",""));
    }


    public String getPluginPath() {
        PreferenceUtils.putString("plug_dir",pluginDir);
        PreferenceUtils.putString("plug_name",pluginName);
        PreferenceUtils.putString("plug_pkg_name",packagename.getText().toString());
        if (pluginDir != null && pluginName != null) {
            return pluginDir + "\\" + pluginName;
        } else {
            return Tf_pluginDir.getText() + "\\" + Tf_pluginname.getText();
        }

    }

    @FXML
    public void setRootPath() {
        //LogUtils.info("设置插件的路径");
        pluginDir = DirectoryWindowsUtils.showDirectoryWindow(openChooseFile,"选择一个目录来作为插件生成根路径");
    }

    @FXML
    public void generatePlugin() {
        if (TextUtils.isEmpty(Tf_pluginDir, Tf_pluginname)) {
            MessageUtils.showMessage("插件根目录或者插件名称不能为空");
            return;
        }
        pluginDir = Tf_pluginDir.getText();
        pluginName = Tf_pluginname.getText();
        if (TextUtils.isEmpty(packagename, versionname)) {
            MessageUtils.showMessage("包名或版本名不能为空哦!");
            return;
        }
        new CustomThread() {
            @Override
            protected void reallyRun() {
                String result = PlugmanUtils.create(pluginDir, pluginName, packagename.getText(), versionname.getText());
                if (FileUtils.fileExist(getPluginPath())) {
                    displayLog.setText("生成插件命令已执行,请前往插件根目录查看\n" + result);
                    boolean packageJson = PlugmanUtils.createPackageJson(getPluginPath(), pluginName, versionname.getText(), packagename.getText());
                    displayLog.setText(displayLog.getText() + "\n" + "packageJson生成" + (packageJson ? "成功" : "失败"));
                } else {
                    displayLog.setText(displayLog.getText().toString()+ "\n" +"插件生成失败\t" + result);
                }
            }
        }.start();

    }

    @FXML
    public void addPlatform() {
        new CustomThread() {
            @Override
            protected void reallyRun() {
                String result = PlugmanUtils.addPlatform(getPluginPath(), "android");
                //然后写入一个package.json
                if (FileUtils.fileExist(PathUtils.getSrcPath(getPluginPath(), "android"))) {
                    displayLog.setText(displayLog.getText().toString() + "\n" +"插件添加平台已经执行\n" + result);

                } else {
                    displayLog.setText(displayLog.getText().toString()+ "\n" +"插件添加平台生成失败\t" + result);
                }
            }
        }.start();
    }

    @FXML
    public void testPlugin() {
        LogUtils.d("暂时不实现");
    }


}
