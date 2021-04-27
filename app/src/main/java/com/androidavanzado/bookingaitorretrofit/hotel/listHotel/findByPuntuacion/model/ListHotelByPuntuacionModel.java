package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.contract.ListHotelByPuntuacionContract;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_BY_PUNTUACION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HOTEL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;

public class ListHotelByPuntuacionModel implements ListHotelByPuntuacionContract.Model {


    private static final String TAG = "ListHotelByPuntuacionModel";

    @Override
    public void getListByPuntuacionLH(OnListHotelByPuntuacionListener onListHotelByPuntuacionListener) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);

        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, HOTEL);
        params.put(QUERY, FIND_BY_PUNTUACION);

        Call<ArrayList<Hotel>> call = apiService.getHotelesByPuntuacion(params);
        call.enqueue(new Callback<ArrayList<Hotel>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<ArrayList<Hotel>> call, Response<ArrayList<Hotel>> response) {
                ArrayList<Hotel> hoteles = response.body();
                Log.d(TAG, "Hoteles por puntuación cargados");
                onListHotelByPuntuacionListener.onResolve(hoteles);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<ArrayList<Hotel>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onListHotelByPuntuacionListener.onReject(t);
            }
        });
    }
}
