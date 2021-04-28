package com.androidavanzado.bookingaitorretrofit.data.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.BOOKING_API_BASE_URL;

public class ApiCLient {
    private static Retrofit retrofit = null;

    public static Retrofit buildClient() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BOOKING_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
}
