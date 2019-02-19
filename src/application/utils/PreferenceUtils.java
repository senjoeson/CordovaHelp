package application.utils;

import java.util.prefs.Preferences;

public class PreferenceUtils {



    private static Preferences mPreferences = Preferences.userRoot();
    private static final String CONFIG_NAME = "config.xml";

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
