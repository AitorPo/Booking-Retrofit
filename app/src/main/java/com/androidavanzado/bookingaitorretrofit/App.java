package com.androidavanzado.bookingaitorretrofit;

import android.app.Application;
import android.content.Context;

// Clase para obtener el contexto de la aplicación desde cualquier lugar de la misma
public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
