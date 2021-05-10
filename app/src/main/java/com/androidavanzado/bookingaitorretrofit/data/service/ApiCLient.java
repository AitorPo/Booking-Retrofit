package com.androidavanzado.bookingaitorretrofit.data.service;

import android.os.Build;

import com.androidavanzado.bookingaitorretrofit.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiCLient {
    private static Retrofit retrofit = null;

    public static Retrofit buildClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        OkHttpClient client = okHttpClientBuilder.build();

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BOOKING_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        return retrofit;
    }
}
