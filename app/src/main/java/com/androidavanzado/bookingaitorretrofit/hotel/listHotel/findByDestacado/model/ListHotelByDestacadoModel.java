package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.contract.ListHotelByDestacadoContract;
import com.androidavanzado.bookingaitorretrofit.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.service.ApiService;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_BY_DESTACADO;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HOTEL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;

public class ListHotelByDestacadoModel implements ListHotelByDestacadoContract.Model {


    private static final String TAG = "ListHotelByDestacadoModel";

    @Override
    public void getDestacadosLH(final OnListHotelByDestacadoListener onListHotelByDestacadoListener) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);

        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, HOTEL);
        params.put(QUERY, FIND_BY_DESTACADO);

        Call<ArrayList<Hotel>> call = apiService.getHotelesByDestacado(params);
        call.enqueue(new Callback<ArrayList<Hotel>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<ArrayList<Hotel>> call, Response<ArrayList<Hotel>> response) {
                ArrayList<Hotel> hoteles = response.body();
                Log.d(TAG, "Hoteles destacados cargados");
                onListHotelByDestacadoListener.onResolve(hoteles);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<ArrayList<Hotel>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onListHotelByDestacadoListener.onReject(t);
            }
        });

    }


}
