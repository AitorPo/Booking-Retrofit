package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.model;

import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.contract.ListHotelByCiudadContract;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_BY_CIUDAD;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HOTEL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;

public class ListHotelByCiudadModel implements ListHotelByCiudadContract.Model {

    private static final String TAG = "ListHotelByCiudadModel";

    @Override
    public void listHotelByCiudadLH(OnListHotelByCiudadListener onListHotelByCiudadListener, int idCiudad) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);
        Map<String, String> params = new HashMap<>();
        params.put(ACTION, HOTEL);
        params.put(QUERY, FIND_BY_CIUDAD);
        params.put(ID, String.valueOf(idCiudad));

        Call<ArrayList<Hotel>> call = apiService.getHotelesByCiudad(params);
        call.enqueue(new Callback<ArrayList<Hotel>>() {
            @Override
            public void onResponse(Call<ArrayList<Hotel>> call, Response<ArrayList<Hotel>> response) {
                ArrayList<Hotel> hoteles = response.body();
                Log.d(TAG, "Lista de hoteles por ciudad cargada");
                onListHotelByCiudadListener.onResolve(hoteles);
            }

            @Override
            public void onFailure(Call<ArrayList<Hotel>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onListHotelByCiudadListener.onReject(t);
            }
        });

    }
}
