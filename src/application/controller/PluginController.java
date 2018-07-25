package application.controller;

import application.cordova.PlugmanUtils;
import application.utils.CustomThread;
import application.utils.DirectoryWindowsUtils;
import application.utils.FileUtils;
import application.utils.MessageUtils;
import application.utils.PathUtils;
import application.utils.TextUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @author MyPC
 * @date 2018/7/19
 * function 生成插件的界面控制器
 */

public class PluginController {
    public ProgressIndicator mProgressIndicator;
    public TextField Tf_pluginDir;
    public TextField Tf_pluginname;
    public TextField versionname;
    public TextField packagename;
    public TextArea displayLog;

    private String pluginDir;
    private String pluginName;
    //暂时所有的平台默认是android
    private String platform;


    public String getPluginPath() {
        if (pluginDir != null && pluginName != null) {
            return pluginDir + "\\" + pluginName;
        } else {
            return Tf_pluginDir.getText() + "\\" + Tf_pluginname.getText();
        }
    }

    @FXML
    public void setRootPath() {
        pluginDir = DirectoryWindowsUtils.showDirectoryWindow("选择一个目录来作为插件生成根路径");
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
                } else {
                    displayLog.setText("插件生成失败\t" + result);
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
                    displayLog.setText("插件添加平台已经执行\n" + result);
                } else {
                    displayLog.setText("插件生成失败\t" + result);
                }
            }
        }.start();
    }

    @FXML
    public void testPlugin() {
        new CustomThread() {
            @Override
            protected void reallyRun() {

            }
        }.start();
    }
}
