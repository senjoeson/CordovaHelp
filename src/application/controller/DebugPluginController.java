package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import application.cordova.CordovaUtils;
import application.utils.CustomThread;
import application.utils.MessageUtils;
import application.utils.TextUtils;

/**
 * @author MyPC
 * @date 2018/7/20
 */

public class DebugPluginController {


    public CheckBox lockproject;
    public CheckBox lockplugin;
    public TextField tf_projectPath;
    public TextField tf_pluginPath;
    public TextArea displayLog;
    public ProgressIndicator mProgressIndicator;


    private String projectPath;
    private String pluginPath;

    @FXML
    public void lockProjectPathInput() {
        tf_projectPath.setEditable(!lockproject.isSelected());
    }

    @FXML
    public void lockPluginPathInput() {
        tf_pluginPath.setEditable(!lockplugin.isSelected());
    }

    @FXML
    public void addPlatform() {
        if (TextUtils.isEmpty(tf_projectPath, tf_pluginPath)) {
            MessageUtils.showMessage("项目路径或者插件路径不能为空");
            return;
        }
        mProgressIndicator.setVisible(true);
        projectPath = tf_projectPath.getText();
        pluginPath = tf_pluginPath.getText();
        new CustomThread() {
            @Override
            protected void reallyRun() {
                String result = CordovaUtils.addPlatform(projectPath, "android");
                displayLog.setText(result);
                mProgressIndicator.setVisible(false);
            }
        }.start();
    }


    @FXML
    public void plugin2project() {
        if (TextUtils.isEmpty(tf_projectPath, tf_pluginPath)) {
            MessageUtils.showMessage("项目路径或者插件路径不能为空");
            return;
        }
        mProgressIndicator.setVisible(true);
        projectPath = tf_projectPath.getText();
        pluginPath = tf_pluginPath.getText();

        new CustomThread() {
            @Override
            protected void reallyRun() {
                String result = CordovaUtils.addPlugin(projectPath, pluginPath);
                displayLog.setText(result);
                mProgressIndicator.setVisible(false);
            }
        }.start();

    }


    @FXML
    public void buildProject() {
        if (TextUtils.isEmpty(tf_projectPath, tf_pluginPath)) {
            MessageUtils.showMessage("项目路径或者插件路径不能为空");
            return;
        }
        mProgressIndicator.setVisible(true);
        projectPath = tf_projectPath.getText();
        pluginPath = tf_pluginPath.getText();
        //
        new CustomThread() {
            @Override
            protected void reallyRun() {
                String result = CordovaUtils.runAndroid(projectPath, "android");
                displayLog.setText(result);
                mProgressIndicator.setVisible(false);
            }
        }.start();

    }


}
