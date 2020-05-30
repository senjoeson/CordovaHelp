package com.senjoeson.cordovahelp.utils;

import java.io.File;
import java.io.FileReader;

public class ReadUtils {


    public static String readFile(String filePath) {
        try {
            if (TextUtils.isEmpty(filePath)) {
                return null;
            }
            File file = new File(filePath, "package.json");
            if (file.length() > 0 && file.exists()) {
                file.setReadable(true);
                file.setWritable(true);
                FileReader fileReader = new FileReader(file);
                char[] chars = new char[1024];
                int num = 0;
                StringBuilder stringBuilder = new StringBuilder();
                while ((num = fileReader.read(chars)) != -1) {
                    stringBuilder.append((new String(chars, 0, num)));
                }
                return stringBuilder.toString();
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }


}
