package com.androidavanzado.bookingaitorretrofit.reserva.myReservas;

import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Reserva;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_BY_USUARIO;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.RESERVA;

public class MyReservasModel implements MyReservasContract.Model{
    private final String TAG = "MyReservasModel";

    @Override
    public void getMyReservasListLH(OnMyReservasListener onMyReservasListener, int idUsuario) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);
        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, RESERVA);
        params.put(QUERY, FIND_BY_USUARIO);
        params.put(ID, String.valueOf(idUsuario));

        Call<ArrayList<Reserva>> call = apiService.getMyReservas(params);
        call.enqueue(new Callback<ArrayList<Reserva>>() {
            @Override
            public void onResponse(Call<ArrayList<Reserva>> call, Response<ArrayList<Reserva>> response) {
                ArrayList<Reserva> reservas = response.body();
                Log.d(TAG, "Mis reservas cargadas");
                onMyReservasListener.onResolve(reservas);
            }

            @Override
            public void onFailure(Call<ArrayList<Reserva>> call, Throwable t) {
                Log.d(TAG, t.toString());
                onMyReservasListener.onReject(t);
            }
        });
    }
}
