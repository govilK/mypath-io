package com.gps.utils;

import android.content.Context;
import android.content.SharedPreferences;





public class Settings {
	
	public static final String PREFS_NAME = "myPath_io_setting";
	public static final String PATH_NAME_LIST = "pathNameList";
	
	// Shared Pref set property functions
    public static void setProperty(String Key, boolean Value, Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        // Commit the edits!
        editor.commit();
    }
    
    public static boolean getBooleanProperty(String key, boolean defaultValue, Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean(key, defaultValue);
    }

    public static void setProperty(String Key, String Value, Context context)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Key, Value);
        // Commit the edits!
        editor.commit();
    }
    
    public static String getStringProperty(String key, String defaultValue, Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(key, defaultValue);
    }

    public static void setProperty(String Key, int Value, Context context)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(Key, Value);
        // Commit the edits!
        editor.commit();
    }

    public static int getIntProperty(String key, int defaultValue, Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt(key, defaultValue);
    }

    public static void setProperty(String Key, float Value, Context context)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(Key, Value);
        // Commit the edits!
        editor.commit();
    }
    
    public static float getFloatProperty(String key, float defaultValue, Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getFloat(key, defaultValue);
    }

    public static void setProperty(String Key, long Value, Context context)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(Key, Value);
        // Commit the edits!
        editor.commit();
    }

    public static long getLongProperty(String key, long defaultValue, Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getLong(key, defaultValue);
    }
}
