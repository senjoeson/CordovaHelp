package application.utils;

import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * @author MyPC
 * @date 2018/7/19
 */

public class DirectoryWindowsUtils {


    private static DirectoryChooser directoryChooser;
    private static Stage stage;

    /**
     * 点击按钮 选取一个文件路径
     * @param openChooseBtn 选择按钮
     * @param title 选择文件的提示
     * @return
     */
    public static String showDirectoryWindow(Button openChooseBtn,String title ) {
        if (null != directoryChooser && stage != null) {
            openChooseBtn.setDisable(false);
            directoryChooser.showDialog(stage);
            MessageUtils.showMessage("选择窗口已存在，请勿重复开启");
            LogUtils.d("directoryChooser不为空");
            return "";
        } else {
            openChooseBtn.setDisable(true);
            stage = new Stage();
            //逻辑流程:  打开系统菜单选择一个路径
            directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle(title);
            File file = directoryChooser.showDialog(stage);
            stage = null;
            if (file != null) {
                openChooseBtn.setDisable(false);
                return file.getAbsolutePath();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "选择目录被取消");
                alert.setTitle("消息提示:");
                alert.show();
                openChooseBtn.setDisable(false);
                return "";
            }

        }


    }

}
