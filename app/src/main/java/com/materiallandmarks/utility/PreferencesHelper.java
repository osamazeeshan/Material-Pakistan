package com.materiallandmarks.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    public static final String PREF_NAME = "wallpaper";
    public static final String PREF_NIGHT_MODE = "wallpaper";

    @SuppressWarnings("deprecation")
    public static final int MODE = Context.MODE_WORLD_WRITEABLE;

    public static SharedPreferences getPreferences(Context context) {
        if(context == null){
            return null;
        }
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public static boolean setKeyValue(Context context, String key, String value) {
        if(context == null) {
            return false;
        }
        try {
            SharedPreferences sharedPreferences = getPreferences(context);
            if(sharedPreferences == null) {
                return false;
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if(editor == null) {
                return false;
            }
            editor.putString(key, value);
            editor.apply();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getKeyValue(Context context, String key, String defValue) {
        if(context == null) {
            return null;
        }
        SharedPreferences sharedPreferences = getPreferences(context);
        if(sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString(key, defValue);
    }

    public static long maxRowId(Context context, String key) {
        try {
            if (context == null) {
                return 0;
            }
            SharedPreferences sharedPreferences = getPreferences(context);
            if (sharedPreferences == null) {
                return 0;
            }
            long incrementValue = sharedPreferences.getLong(key, 0);
            incrementValue++;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (editor == null) {
                return 0;
            }
            editor.putLong(key, incrementValue);
            editor.apply();
            return incrementValue;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isKeyExists(Context context, String key) {
        if(context == null) {
            return false;
        }
        SharedPreferences sharedPreferences = getPreferences(context);
        if(sharedPreferences.contains(key)) {
            return true;
        }
        return false;
    }
}
