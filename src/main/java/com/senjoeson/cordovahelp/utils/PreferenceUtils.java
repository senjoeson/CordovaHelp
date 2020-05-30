package com.senjoeson.cordovahelp.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.prefs.Preferences;

public class PreferenceUtils {



    private static Preferences mPreferences = Preferences.userRoot();
    private static final String CONFIG_NAME = "CordovaConfig.xml";


    public  static  boolean  putString(String key,String value)  {

        mPreferences.put(key, value);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(CONFIG_NAME);
            mPreferences.exportNode(outputStream);
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public  static  String  getString(String key,String defValue)  {
        String result = mPreferences.get(key, defValue);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(CONFIG_NAME);
            mPreferences.exportNode(outputStream);
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    /*public static <T> void putValue(String key, T object) {
        mPreferences = mPreferences.node("/path");
        if (object instanceof String) {
            mPreferences.put(key, (String) object);
        } else if (object instanceof Integer) {
            mPreferences.putInt(key, (int) object);
        } else if (object instanceof Boolean) {
            mPreferences.putBoolean(key, (boolean) object);
        } else if (object instanceof byte[]) {
            mPreferences.putByteArray(key, (byte[]) object);
        } else if (object instanceof Double) {
            mPreferences.putDouble(key, (double) object);
        } else if (object instanceof Long) {
            mPreferences.putDouble(key, (long) object);
        } else if (object instanceof Float) {
            mPreferences.putFloat(key, (float) object);
        }
        try {
            mPreferences.exportNode(new FileOutputStream(CONFIG_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/



}
