package application.utils;

import java.io.File;

import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * @author MyPC
 * @date 2018/7/19
 */

public class DirectoryWindowsUtils {

private  static  Stage stage;
    /**
     * 设置cordova的根目录
     */
    public static String showDirectoryWindow(String  title) {
        if (null != stage) {
            MessageUtils.showMessage("您已经开了一个选择窗口");
            return "";
        } else {
            stage = new Stage();
        }
        //逻辑流程:  打开系统菜单选择一个路径
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            return file.getAbsolutePath();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "选择目录被取消");
            alert.setTitle("消息提示:");
            alert.show();
            stage = null;
            return "";
        }
    }

}
