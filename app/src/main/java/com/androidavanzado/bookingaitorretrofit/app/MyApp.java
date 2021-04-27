package com.androidavanzado.bookingaitorretrofit.app;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    //instancia de esta clase para poder usarla
    private static MyApp instance;

    public static MyApp getInstance() { return instance; }

    public static Context getContext() { return instance; }

    //damos valor inicial a instance

    @Override
    public void onCreate() {
        //damos a instance el valor de la clase en la que nos encontrmaos
        instance = this;
        super.onCreate();
    }
}
