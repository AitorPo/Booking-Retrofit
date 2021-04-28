package com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.contract.ListHabitacionByHotelContract;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_ALL_BY_HOTEL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HABITACION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID_HOTEL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;
/*import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.view.ListHotelByCiudadActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.view.ListHotelByReservasActivity;*/

public class ListHabitacionByHotelModel implements ListHabitacionByHotelContract.Model {
    private final String TAG = "ListHabitacionByHotelModel";

    @Override
    public void getHabitacionListLH(OnListHabitacionByHotelListener onListHabitacionByHotelListene, int idHotel) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);
        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, HABITACION);
        params.put(QUERY, FIND_ALL_BY_HOTEL);
        params.put(ID_HOTEL, String.valueOf(idHotel));

        Call<ArrayList<Habitacion>> call = apiService.getAllHabitaciones(params);
        call.enqueue(new Callback<ArrayList<Habitacion>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<ArrayList<Habitacion>> call, Response<ArrayList<Habitacion>> response) {
                ArrayList<Habitacion> habitaciones = response.body();
                Log.d(TAG, "Lista de habitaciones cargada");
                onListHabitacionByHotelListene.onResolve(habitaciones);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<ArrayList<Habitacion>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onListHabitacionByHotelListene.onReject(t);
            }
        });
    }
}
