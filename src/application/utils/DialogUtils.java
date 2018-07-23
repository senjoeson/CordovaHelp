package application.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * @author MyPC
 * @date 2018/7/19
 */

public class DialogUtils {
    /**
     * 弹出一个通用的确定对话框
     *
     * @param message 对话框的信息
     * @return 用户点击了是或否
     */
    public static void showConfirmDialog(String message, CallBack callBack) {
//        按钮部分可以使用预设的也可以像这样自己 new 一个
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, new ButtonType("取消", ButtonBar.ButtonData.NO),
                new ButtonType("确定", ButtonBar.ButtonData.YES));
//        设置窗口的标题
        alert.setTitle("提示:");
        alert.setContentText(message);
//        设置对话框的 icon 图标，参数是主窗口的 stage
        //  alert.initOwner(d_stage);
//        showAndWait() 将在对话框消失以前不会执行之后的代码
        Optional<ButtonType> buttonType = alert.showAndWait();
//        根据点击结果返回
        if (buttonType != null && buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
            callBack.onConfirm();
        } else {
            callBack.onCancel();
        }
    }

    //    弹出一个信息对话框
    public void f_alert_informationDialog(String title, String p_message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示:");
        alert.setHeaderText(title);
        alert.setContentText(p_message);
        //   alert.initOwner(d_stage);
        alert.show();
    }


    public interface CallBack {
        void onConfirm();

        void onCancel();
    }
}
