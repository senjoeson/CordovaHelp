package com.senjoeson.cordovahelp.utils;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean isEmpty(String... content) {
        for (String s : content) {
            if (isEmpty(s)) {
                return false;
            }
        }
        return true;
    }


    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    public static void main(String[] args) {
        System.out.println(checkEmail("a562791907@qq.com"));
    }


}
