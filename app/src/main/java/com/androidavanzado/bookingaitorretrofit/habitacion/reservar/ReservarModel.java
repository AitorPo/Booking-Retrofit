package com.androidavanzado.bookingaitorretrofit.habitacion.reservar;

import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Reserva;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ADD;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID_HAB;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.IN;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.OUT;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.RESERVA;

public class ReservarModel implements ReservarContract.Model{
    private static final String TAG = "ReservarModel";

    @Override
    public void getReserva(onReservaListener onReservaListener, Reserva reserva) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);

        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, RESERVA);
        params.put(QUERY, ADD);
        params.put(ID, String.valueOf(reserva.getIdUsuario()));
        params.put(ID_HAB, String.valueOf(reserva.getIdHabitacion()));
        params.put(IN, String.valueOf(reserva.getIn()));
        params.put(OUT, String.valueOf(reserva.getOut()));

        Call<Void> call = apiService.reservar(params);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                String r = "Reserva realizada";
                Log.d(TAG, r);
                Log.d(TAG, String.valueOf(response.code()));
                Log.d(TAG, String.valueOf(response.headers()));
                if(response.isSuccessful())
                    onReservaListener.onResolve(r);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onReservaListener.onReject(t);
                Log.d(TAG, t.toString());
            }
        });



    }
}
