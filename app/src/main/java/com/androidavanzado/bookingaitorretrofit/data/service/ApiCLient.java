package com.androidavanzado.bookingaitorretrofit.data.service;

import android.os.Build;

import com.androidavanzado.bookingaitorretrofit.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiCLient {
    private static Retrofit retrofit = null;

    public static Retrofit buildClient() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BOOKING_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
}
