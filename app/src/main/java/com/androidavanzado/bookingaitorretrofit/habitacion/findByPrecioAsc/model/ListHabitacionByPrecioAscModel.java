package com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.contract.ListHabitacionByPrecioAscContract;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_BY_PRECIO_ASC;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HABITACION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;

public class ListHabitacionByPrecioAscModel implements ListHabitacionByPrecioAscContract.Model {

    private static final String TAG = "ListHabitacionByPrecioAscModel";

    @Override
    public void listHabitacionPrecioAsc(OnListPrecioAscListener onListPrecioAscListener) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);

        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, HABITACION);
        params.put(QUERY, FIND_BY_PRECIO_ASC);

        Call<ArrayList<Habitacion>> call = apiService.getHabitacionByPrecioAsc(params);
        call.enqueue(new Callback<ArrayList<Habitacion>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<ArrayList<Habitacion>> call, Response<ArrayList<Habitacion>> response) {
                ArrayList<Habitacion> habitaciones = response.body();
                Log.d(TAG, "Habitaciones por precio ASC cargados");
                onListPrecioAscListener.onResolve(habitaciones);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<ArrayList<Habitacion>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onListPrecioAscListener.onReject(t);
            }
        });
    }
}
