package com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.model;

import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Ciudad;
import com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.contract.ListCiudadContract;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.CIUDAD;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_ALL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;

public class ListCiudadModel implements ListCiudadContract.Model {

    private static final String TAG = "ListCiudadModel";

    @Override
    public void getListCiudadLH(final OnListCiudadModelListener onListCiudadModelListener) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);
        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, CIUDAD);
        params.put(QUERY, FIND_ALL);

        Call<ArrayList<Ciudad>> call = apiService.getAllCiudades(params);
        call.enqueue(new Callback<ArrayList<Ciudad>>() {
            @Override
            public void onResponse(Call<ArrayList<Ciudad>> call, Response<ArrayList<Ciudad>> response) {
                ArrayList<Ciudad> ciudades = response.body();
                Log.d(TAG, "Lista de ciudades cargada");
                onListCiudadModelListener.onResolve(ciudades);
            }

            @Override
            public void onFailure(Call<ArrayList<Ciudad>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onListCiudadModelListener.onReject(t);
            }
        });
    }
}
