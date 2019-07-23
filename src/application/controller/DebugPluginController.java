package application.controller;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import application.bean.PackageJson;
import application.cordova.CordovaUtils;
import application.utils.CustomThread;
import application.utils.LogUtils;
import application.utils.MessageUtils;
import application.utils.PreferenceUtils;
import application.utils.ReadUtils;
import application.utils.TextUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

/**
 * @author MyPC
 * @date 2018/7/20
 */

public class DebugPluginController implements Initializable {


	public CheckBox lockProject;
	public CheckBox lockPlugin;
	public TextField tf_projectPath;
	public TextField tf_pluginPath;
	public TextArea displayLog;
	public ProgressIndicator mProgressIndicator;
	public Button btnAddPlatform;
	public Button btnReAddPlugin;


	private String projectPath;
	private String pluginPath;
	private String pluginPackageName;

	@FXML
	public void lockProjectPathInput() throws IOException, BackingStoreException {
		tf_projectPath.setEditable(!lockProject.isSelected());
		if (lockProject.isSelected() && !tf_projectPath.getText().isEmpty()) {
			//记录本次设置
			PreferenceUtils.putString("projectPath", tf_projectPath.getText());

		}
	}

	@FXML
	public void lockPluginPathInput() throws IOException, BackingStoreException {
		tf_pluginPath.setEditable(!lockPlugin.isSelected());

		if (lockPlugin.isSelected() && !tf_pluginPath.getText().isEmpty()) {
			PreferenceUtils.putString("pluginPath", tf_pluginPath.getText());

		}
	}

	/**
	 * 获取插件的包名
	 *
	 * @return
	 */
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
		if (TextUtils.isEmpty(tf_projectPath)) {
			MessageUtils.showMessage("项目路径不能为空");
			return;
		}
		getKeyPath();
		mProgressIndicator.setVisible(true);
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
		getKeyPath();
		mProgressIndicator.setVisible(true);
		new CustomThread() {
			@Override
			protected void reallyRun() {
				String result = CordovaUtils.addPlugin(projectPath, pluginPath);
				displayLog.setText(result);
				mProgressIndicator.setVisible(false);
			}
		}.start();

	}

	/**
	 * 获取关键的路径
	 */
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
		mProgressIndicator.setVisible(true);
		new CustomThread() {
			@Override
			protected void reallyRun() {
				String result = CordovaUtils.showPluginList(projectPath);
				displayLog.setText(result);
				mProgressIndicator.setVisible(false);
			}
		}.start();
	}

	@FXML
	public void reinstallPlugin() {
		LogUtils.d("重新集成插件到项目");
		//判断是否设置了Cordova项目
		getKeyPath();
		if (TextUtils.isEmpty(projectPath)) {
			MessageUtils.showMessage("请先设置一个Cordova项目路径,然后重试");
			return;
		}
		mProgressIndicator.setVisible(true);
		new CustomThread() {
			@Override
			protected void reallyRun() {

				displayLog.setText(CordovaUtils.rmPlugin(projectPath, getPluginPackageName()) + "\n" + CordovaUtils.addPlugin(projectPath, pluginPath));
				mProgressIndicator.setVisible(false);
			}
		}.start();

	}

	@FXML
	public void removePlugin(MouseEvent mouseEvent) {
		LogUtils.d("移除集成插件到项目");
		getKeyPath();
		if (TextUtils.isEmpty(projectPath)) {
			MessageUtils.showMessage("请先设置一个Cordova项目路径,然后重试");
			return;
		}

		mProgressIndicator.setVisible(true);
		new CustomThread() {
			@Override
			protected void reallyRun() {
				displayLog.setText(CordovaUtils.rmPlugin(projectPath, getPluginPackageName()));
				mProgressIndicator.setVisible(false);
			}
		}.start();
	}

	public void clearLog(MouseEvent mouseEvent) {
		displayLog.clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//设置初始化值

		String projectPath = PreferenceUtils.getString("projectPath", "");
		String pluginPath = PreferenceUtils.getString("pluginPath", "");
		if (!TextUtils.isEmpty(projectPath)) {
			tf_projectPath.setText(projectPath);
		}
		if (!TextUtils.isEmpty(pluginPath)) {
			tf_pluginPath.setText(pluginPath);
		}
		btnAddPlatform.setTooltip(new Tooltip("目前仅支持android"));

		btnReAddPlugin.setTooltip(new Tooltip("移除后再添加插件"));
		lockProject.setTooltip(new Tooltip("点击锁定后,路径保存在本地."));
		lockPlugin.setTooltip(new Tooltip("点击锁定后,路径保存在本地."));
	}

	public void removePlatform(MouseEvent mouseEvent) {
		LogUtils.d("为项目移除平台操作");
		if (TextUtils.isEmpty(tf_projectPath, tf_pluginPath)) {
			MessageUtils.showMessage("项目路径或者插件路径不能为空");
			return;
		}
		getKeyPath();
		mProgressIndicator.setVisible(true);
		new CustomThread() {
			@Override
			protected void reallyRun() {
				String result = CordovaUtils.rmPlatform(projectPath, "android");
				displayLog.setText(result);
				mProgressIndicator.setVisible(false);
			}
		}.start();
	}
}
