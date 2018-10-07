package com.pro.ahmed.jamiya.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.pro.ahmed.jamiya.R;

public class DataProcessor {

    private static Context context;
    private static DataProcessor mInstance;

    private SharedPreferences sharedPref;
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;
    private final static String PREFS_NAME = "jamiya";


    private DataProcessor(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static DataProcessor getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DataProcessor(context);
        }
        return mInstance;
    }

    public static void setInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key) {
        return prefs.getInt(key, -1);
    }

    public static void setStr(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStr(String key) {
        return prefs.getString(key, "DNF");
    }

    public static void setBool(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBool(String key) {
        return prefs.getBoolean(key, false);
    }
}