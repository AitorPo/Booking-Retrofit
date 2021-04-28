package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.contract.ListHotelByReservasContract;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HOTEL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.RANKING_RESERVAS;

public class ListHotelByReservasModel implements ListHotelByReservasContract.Model {

    private static final String TAG = "ListHotelByReservasModel";

    @Override
    public void getHotelesReservasLH(OnListHotelByReservasListener onListHotelByReservasListener) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);

        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, HOTEL);
        params.put(QUERY, RANKING_RESERVAS);

        Call<ArrayList<Hotel>> call = apiService.getHotelesByReservas(params);
        call.enqueue(new Callback<ArrayList<Hotel>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<ArrayList<Hotel>> call, Response<ArrayList<Hotel>> response) {
                ArrayList<Hotel> hoteles = response.body();
                Log.d(TAG, "Hoteles por reservas cargados");
                onListHotelByReservasListener.onResolve(hoteles);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<ArrayList<Hotel>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onListHotelByReservasListener.onReject(t);
            }
        });
    }
}
