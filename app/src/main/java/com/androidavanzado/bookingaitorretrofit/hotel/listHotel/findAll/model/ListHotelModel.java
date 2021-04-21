package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.model;


import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Ciudad;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.contract.ListHotelContract;
import com.androidavanzado.bookingaitorretrofit.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.service.ApiService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.CIUDAD;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_BY_ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HOTEL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_ALL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;

public class ListHotelModel implements ListHotelContract.Model {
    private final String TAG = "ListHotelModel";

    @Override
    public void getHotelesLH(final OnListHotelListener onListHotelListener) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);
        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, HOTEL);
        params.put(QUERY, FIND_ALL);


        Call<ArrayList<Hotel>> call = apiService.getAllHotels(params);
        call.enqueue(new Callback<ArrayList<Hotel>>() {
            @Override
            public void onResponse(Call<ArrayList<Hotel>> call, Response<ArrayList<Hotel>> response) {
                ArrayList<Hotel> hotels = response.body();
                Log.d(TAG, "Lista de hoteles cargada");
                onListHotelListener.onResolve(hotels);
            }

            @Override
            public void onFailure(Call<ArrayList<Hotel>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onListHotelListener.onReject(t);
            }
        });
    }
}


