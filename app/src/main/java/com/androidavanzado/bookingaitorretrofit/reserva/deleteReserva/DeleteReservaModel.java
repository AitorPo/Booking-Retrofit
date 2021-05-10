package com.androidavanzado.bookingaitorretrofit.reserva.deleteReserva;

import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Reserva;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.DELETE_BY_ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.RESERVA;

public class DeleteReservaModel implements DeleteReservaContract.Model{

    private static final String TAG = "DeleteReservaModel";

    @Override
    public void deleteReservaLH(onDeleteReservaListener onDeleteReservaListener, int idReserva) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);

        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, RESERVA);
        params.put(QUERY, DELETE_BY_ID);
        params.put(ID, String.valueOf(idReserva));
        Log.d(TAG, "deleteReservaLH: " + idReserva);

        Call<Void> call = apiService.deleteReserva("BookingAitor/Controller", params);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                String r = "Reserva eliminada correctamente";

                Log.d(TAG, r);
                Log.d(TAG, String.valueOf(response.code()));
                Log.d(TAG, String.valueOf(response.headers()));
                if(response.isSuccessful())
                    onDeleteReservaListener.onResolve(r);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                onDeleteReservaListener.onReject(t);
            }
        });
    }
}
