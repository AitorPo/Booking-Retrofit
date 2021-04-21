package com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.model;

import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.contract.DetailsHabitacionContract;
import com.androidavanzado.bookingaitorretrofit.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.service.ApiService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_BY_ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HABITACION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;
/*import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.view.ListHabitacionByPrecioAscActivity;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.view.ListHabitacionByPrecioDescActivity;*/

public class DetailsHabitacionModel implements DetailsHabitacionContract.Model {
    private final String TAG = "DetailsHabitacionModel";

    @Override
    public void getHabitacionLS(OnDetailsHabitacionListener onDetailsHabitacionListener, int idHabitacion) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);

        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, HABITACION);
        params.put(QUERY, FIND_BY_ID);
        params.put(ID, String.valueOf(idHabitacion));

        Call<Habitacion> call = apiService.getHabitacionById(params);
        call.enqueue(new Callback<Habitacion>() {
            @Override
            public void onResponse(Call<Habitacion> call, Response<Habitacion> response) {
                Habitacion habitacion = response.body();
                Log.d(TAG, "Habitación cargada");
                onDetailsHabitacionListener.onResolve(habitacion);
            }

            @Override
            public void onFailure(Call<Habitacion> call, Throwable t) {
                Log.e(TAG, "Error al obtener la habitación");
                onDetailsHabitacionListener.onReject(t);
            }
        });
    }
}
