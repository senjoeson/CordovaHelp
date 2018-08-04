package application.utils;

import javafx.scene.control.TextField;

/**
 * @author MyPC
 * @date 2018/7/20
 */

public class TextUtils {
    /**
     * 判断是否输入框的内容是否为空
     *
     * @param textFields 要判断的输入框
     * @return 是否为空
     */
    public static boolean isEmpty(TextField... textFields) {
        for (TextField textField : textFields) {
            if ("".equals(textField.getText().trim())) {
                return true;
            }
        }
        return false;
    }


    public static boolean isEmpty(String content) {
        return !(content != null && content.length() > 0);
    }

}
