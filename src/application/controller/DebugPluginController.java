package application.controller;

import com.google.gson.Gson;

import application.bean.PackageJson;
import application.cordova.CordovaUtils;
import application.utils.CustomThread;
import application.utils.LogUtils;
import application.utils.MessageUtils;
import application.utils.ReadUtils;
import application.utils.TextUtils;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private String pluginPackageName;

    @FXML
    public void lockProjectPathInput() {
        tf_projectPath.setEditable(!lockproject.isSelected());
    }

    @FXML
    public void lockPluginPathInput() {
        tf_pluginPath.setEditable(!lockplugin.isSelected());
    }


    public String getPluginPackageName() {
        if (TextUtils.isEmpty(pluginPath) && TextUtils.isEmpty(tf_pluginPath)) {
            MessageUtils.showMessage("插件路径不能为空");
            return null;
        }
        getKeyPath();
        //读取当前插件中的package.json 然后进行解析
        String butter = ReadUtils.readFile(pluginPath);
        PackageJson packageJson = new Gson().fromJson(butter, PackageJson.class);
        return packageJson.getCordova().getId();
    }

    @FXML
    public void addPlatform() {
        LogUtils.d("给项目添加平台");
        if (TextUtils.isEmpty(tf_projectPath, tf_pluginPath)) {
            MessageUtils.showMessage("项目路径或者插件路径不能为空");
            return;
        }
        mProgressIndicator.setVisible(true);
        getKeyPath();
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
    public void addPlugin2project() {
        LogUtils.d("给项目添加插件");
        if (TextUtils.isEmpty(tf_projectPath, tf_pluginPath)) {
            MessageUtils.showMessage("项目路径或者插件路径不能为空");
            return;
        }
        mProgressIndicator.setVisible(true);
        getKeyPath();

        new CustomThread() {
            @Override
            protected void reallyRun() {
                String result = CordovaUtils.addPlugin(projectPath, pluginPath);
                displayLog.setText(result);
                mProgressIndicator.setVisible(false);
            }
        }.start();

    }

    private void getKeyPath() {
        projectPath = tf_projectPath.getText();
        pluginPath = tf_pluginPath.getText();
    }


    @FXML
    public void buildProject() {
        LogUtils.d("编译项目");
        if (TextUtils.isEmpty(tf_projectPath, tf_pluginPath)) {
            MessageUtils.showMessage("项目路径或者插件路径不能为空");
            return;
        }
        mProgressIndicator.setVisible(true);
        getKeyPath();
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

    @FXML
    public void showPluginList() {
        LogUtils.d("显示当前项目中的所有插件");
        //判断是否设置了Cordova项目
        projectPath = tf_projectPath.getText();
        if (TextUtils.isEmpty(projectPath)) {
            MessageUtils.showMessage("请先设置一个Cordova项目路径,然后重试");
            return;
        }
        new CustomThread() {
            @Override
            protected void reallyRun() {
                String result = CordovaUtils.showPluginList(projectPath);
                displayLog.setText(result);
            }
        }.start();
    }

    @FXML
    public void replugin() {
        LogUtils.d("重新集成插件到项目");
        //判断是否设置了Cordova项目
        getKeyPath();
        if (TextUtils.isEmpty(projectPath)) {
            MessageUtils.showMessage("请先设置一个Cordova项目路径,然后重试");
            return;
        }
        new CustomThread() {
            @Override
            protected void reallyRun() {
                displayLog.setText(CordovaUtils.rmPlugin(projectPath, getPluginPackageName()) + "\n" + CordovaUtils.addPlugin(projectPath, pluginPath));
            }
        }.start();

    }
}
