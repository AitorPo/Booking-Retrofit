package com.androidavanzado.bookingaitorretrofit.utils;

import android.content.SharedPreferences;

// Nos ahorramos escribir varias veces el mismo código para gestionar las SharedPreferences
public class Util {

    public static String getUserMailPrefs(SharedPreferences sharedPreferences){
        return sharedPreferences.getString("Email", "");
    }

    public static String getUserPasswordPrefs(SharedPreferences sharedPreferences){
        return sharedPreferences.getString("Password", "");
    }
}
