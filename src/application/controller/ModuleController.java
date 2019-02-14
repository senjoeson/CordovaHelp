package application.controller;

import java.io.File;

import application.cordova.CordovaUtils;
import application.utils.CustomThread;
import application.utils.DirectoryWindowsUtils;
import application.utils.FileUtils;
import application.utils.MessageUtils;
import application.utils.TextUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @author MyPC
 * @date 2018/6/1
 */

public class ModuleController {

    public TextField moduleName;
    public TextField packageName;
    public TextField versionName;
    public TextArea displayLog;


    @FXML
    TextField parentDir;

    @FXML
    ProgressIndicator mProgressIndicator;


    private String mModuleName = null;
    private String rootPath = null;

    private CustomThread mThread;

    /**
     * 获取项目的路径
     *
     * @return
     */
        public String getModulePath() {
        if (rootPath != null && mModuleName != null) {
            return rootPath + "\\" + mModuleName;
        } else {
            return parentDir.getText() + "\\" + moduleName.getText();
        }
    }

    /**
     * 选择目录
     * 选择Cordova项目在哪里生成
     */
    @FXML
    private void setParentDir() {
        rootPath = DirectoryWindowsUtils.showDirectoryWindow("选择一个根目录来作为cordova生成项目的根路径");
        parentDir.setText(rootPath);
    }


    @FXML
    public void generateModule() {
        mProgressIndicator.setVisible(true);
        if (TextUtils.isEmpty(parentDir)) {
            MessageUtils.showMessage("根目录不能为空!");
            return;
        }
        if (TextUtils.isEmpty(moduleName, packageName)) {
            MessageUtils.showMessage("项目名称或包名不能为空!");
        } else {
            String moduleName = this.moduleName.getText();
            String packageName = this.packageName.getText();
            File file = new File(getModulePath());
            if (!file.exists()) {
                //   List<String> cordovaCreate = ComOrderUtils.createCordova(parentDir.getText(), moduleName, packageName);
                mThread = new CustomThread() {
                    @Override
                    protected void reallyRun() {
                        String runCmd = CordovaUtils.create(parentDir.getText(), moduleName, packageName);
                        displayLog.setText(runCmd);
                        mProgressIndicator.setVisible(false);
                    }
                };
                mThread.start();
            } else {
                boolean delAllFile = FileUtils.deleteDirectory(getModulePath());
                if (delAllFile) {
                    new Thread(() -> {
                        String runCmd = CordovaUtils.create(parentDir.getText(), moduleName, packageName);
                        displayLog.setText(runCmd);
                        mProgressIndicator.setVisible(false);
                    }).start();

                } else {
                    displayLog.setText("您选择的目录已存在该项目");
                    mProgressIndicator.setVisible(false);
                }
            }


        }

    }



    /**
     * 给Cordova项目添加平台
     */
    @FXML
    public void addPlatform() {
        if (getModulePath() == null) {
            MessageUtils.showMessage("请先生成一个Cordova项目");
            return;
        }
        mProgressIndicator.setVisible(true);
        mThread = new CustomThread() {
            @Override
            protected void reallyRun() {
                String result = CordovaUtils.addPlatform(getModulePath(), "android");
                displayLog.setText( result);
                mProgressIndicator.setVisible(false);
            }
        };
        mThread.start();
    }

    @FXML
    public void testProject() {

        /*DialogUtils.showConfirmDialog("请确认您已经连接了手机或者模拟器", new DialogUtils.CallBack() {
            @Override
            public void onConfirm() {
               new Thread(() -> {
                    mProgressIndicator.setVisible(true);
                    String result = CordovaUtils.runAndroid("C:\\Users\\MyPC\\IdeaProjects\\TestDemo", "android");
                    displayLog.setText(displayLog.getText() + "\n" + result);
                    mProgressIndicator.setVisible(false);
                }).start();
            }

            @Override
            public void onCancel() {

            }
        });*/
        mThread = new CustomThread() {
            @Override
            protected void reallyRun() {
                mProgressIndicator.setVisible(true);
                String result = CordovaUtils.runAndroid("C:\\Users\\MyPC\\IdeaProjects\\TestDemo", "android");
                displayLog.setText( result);
                mProgressIndicator.setVisible(false);
            }
        };
        mThread.start();

    }

    @FXML
    public void stopRunAnyOrder() {
        //mThread.stopTask();
       // String result = DosUtils.stopMyOrder();
        //displayLog.setText(displayLog.getText() + "\n" + result);
    }
}