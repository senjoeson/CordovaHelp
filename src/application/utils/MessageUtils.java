package application.utils;

import javafx.scene.control.Alert;

/**
 * @author MyPC
 * @date 2018/6/1
 */

public class MessageUtils {

    private static Alert mAlert;

    private MessageUtils() {

    }


    public static void showMessage(String message) {
        mAlert = new Alert(Alert.AlertType.WARNING, message);
        mAlert.setTitle("消息提示:");
        mAlert.show();
    }

    public static void showMessage(String message, Alert.AlertType alertType) {
        mAlert = new Alert(alertType, message);
        mAlert.setTitle("消息提示:");
        mAlert.show();
    }

    public static void close() {
        if (mAlert != null)
            mAlert.close();
    }
}
