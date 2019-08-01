package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.cordova.CordovaUtils;
import application.utils.CustomThread;
import application.utils.DialogUtils;
import application.utils.DirectoryWindowsUtils;
import application.utils.FileUtils;
import application.utils.LogUtils;
import application.utils.MessageUtils;
import application.utils.PreferenceUtils;
import application.utils.TextUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * @author MyPC
 * @date 2018/6/1
 */

public class ModuleController implements Initializable {

	public TextField moduleName;
	public TextField packageName;
	public TextField versionName;
	public TextArea displayLog;
	public Button openChooseFile;
	public TextField platform_version;


	@FXML
	TextField parentDir;

	@FXML
	ProgressIndicator mProgressIndicator;


	private String mModuleName = null;
	private String rootPath = null;

	private CustomThread mThread;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		parentDir.setText(PreferenceUtils.getString("module_rootPath", ""));
		moduleName.setText(PreferenceUtils.getString("module_mod_name", ""));
		packageName.setText(PreferenceUtils.getString("module_pkg_name", ""));
	}


	/**
	 * 获取项目的路径
	 *
	 * @return
	 */
	public String getModulePath() {
		rootPath = parentDir.getText().toString();
		mModuleName = moduleName.getText().toString();
		PreferenceUtils.putString("module_rootPath", rootPath);
		PreferenceUtils.putString("module_mod_name", moduleName.getText().toString());
		PreferenceUtils.putString("module_pkg_name", packageName.getText().toString());
		if (rootPath != null && mModuleName != null) {
			return rootPath + "\\" + mModuleName;
		} else {
			return null;
		}
	}



	/**
	 * 选择目录
	 * 选择Cordova项目在哪里生成
	 */
	@FXML
	private void setParentDir() {
		rootPath = DirectoryWindowsUtils.showDirectoryWindow(openChooseFile, "选择一个根目录来作为cordova生成项目的根路径");
		parentDir.setText(rootPath);
	}


	@FXML
	public void generateModule() {
		if (TextUtils.isEmpty(parentDir)) {
			MessageUtils.showMessage("根目录不能为空!");
			return;
		}
		if (TextUtils.isEmpty(moduleName, packageName)) {
			MessageUtils.showMessage("项目名称或包名不能为空!");
			return;
		}


		mProgressIndicator.setVisible(true);
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


	/**
	 * 给Cordova项目添加平台
	 */
	@FXML
	public void addPlatform() {
		if (getModulePath()==null) {
			MessageUtils.showMessage("未检测到Cordova项目");
			return;
		}

		String plat_ver="android";
		if (!TextUtils.isEmpty(platform_version.getText().toString())){
			plat_ver= plat_ver.concat("@").concat(platform_version.getText().toString());
		}
		LogUtils.d("平台版本:"+plat_ver);
		mProgressIndicator.setVisible(true);
		String finalPlat_ver = plat_ver;
		mThread = new CustomThread() {
			@Override
			protected void reallyRun() {
				String result = CordovaUtils.addPlatform(getModulePath(), finalPlat_ver);
				displayLog.setText(result);
				mProgressIndicator.setVisible(false);
			}
		};
		mThread.start();
	}

	@FXML
	public void testProject() {
		if (getModulePath()==null) {
			MessageUtils.showMessage("未检测到Cordova项目");
			return;
		}
		DialogUtils.showConfirmDialog("请确认您已经连接了手机或者模拟器", new DialogUtils.CallBack() {
			@Override
			public void onConfirm() {
				new Thread(() -> {
					mProgressIndicator.setVisible(true);
					String result = CordovaUtils.runAndroid(getModulePath(), "android");
					displayLog.setText(displayLog.getText() + "\n" + result);
					mProgressIndicator.setVisible(false);
				}).start();
			}

			@Override
			public void onCancel() {

			}
		});
		mThread = new CustomThread() {
			@Override
			protected void reallyRun() {
				mProgressIndicator.setVisible(true);
				String result = CordovaUtils.runAndroid(getModulePath(), "android");
				displayLog.setText(result);
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

	@FXML
	public void rmPlatform(MouseEvent mouseEvent) {
		if (getModulePath()==null) {
			MessageUtils.showMessage("未检测到Cordova项目");
			return;
		}
		mProgressIndicator.setVisible(true);
		mThread = new CustomThread() {
			@Override
			protected void reallyRun() {
				String result = CordovaUtils.rmPlatform(getModulePath(), "android");
				displayLog.setText(result);
				mProgressIndicator.setVisible(false);
			}
		};
		mThread.start();
	}


}