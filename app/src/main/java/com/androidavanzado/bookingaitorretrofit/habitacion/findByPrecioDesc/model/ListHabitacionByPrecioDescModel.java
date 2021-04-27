package com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.contract.ListHabitacionByPrecioDescContract;
import com.androidavanzado.bookingaitorretrofit.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.service.ApiService;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_BY_PRECIO_DESC;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HABITACION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;

public class ListHabitacionByPrecioDescModel implements ListHabitacionByPrecioDescContract.Model {

    private static final String TAG = "ListHabitacionByPrecioDescModel";

    @Override
    public void listHabitacionPrecioDesc(OnListPrecioDescListener onListPrecioDescListener) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);

        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, HABITACION);
        params.put(QUERY, FIND_BY_PRECIO_DESC);

        Call<ArrayList<Habitacion>> call = apiService.getHabitacionByPrecioDesc(params);
        call.enqueue(new Callback<ArrayList<Habitacion>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<ArrayList<Habitacion>> call, Response<ArrayList<Habitacion>> response) {
                ArrayList<Habitacion> habitaciones = response.body();
                Log.d(TAG, "Hoteles por precio DESC cargados");
                onListPrecioDescListener.onResolve(habitaciones);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<ArrayList<Habitacion>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onListPrecioDescListener.onReject(t);
            }
        });
    }
}
