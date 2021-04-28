package com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.model;

import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;
import com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.contract.DetailsHotelContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.model.HotelRepository;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_BY_ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HOTEL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ID;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;

/*import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.view.ListHotelActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.view.ListHotelByCiudadActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.view.ListHotelByDestacadoActivity;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.view.ListHotelByPuntuacionActivity;
import com.androidavanzado.bookingaitorretrofit.utils.Post;*/

public class DetailsHotelModel implements DetailsHotelContract.Model {
    private final String TAG = "DetailsHotelModel";
    @Override
    public void getHotelLS(OnDetailsHotelListener onDetailsHotelListener, int idHotel) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);
        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, HOTEL);
        params.put(QUERY, FIND_BY_ID);
        params.put(ID, String.valueOf(idHotel));

        HotelRepository repository = new HotelRepository();

        Call<Hotel> call = apiService.getHotelById(params);
        call.enqueue(new Callback<Hotel>() {
            @Override
            public void onResponse(Call<Hotel> call, Response<Hotel> response) {
                Hotel hotel = response.body();
                Hotel hotelRoomDataBase = repository.getHotelDetailsRoom(idHotel);
                Log.d(TAG, hotelRoomDataBase.toString());
                Log.d(TAG, "Detalles cargados");
                // Cargamos los datos del hotel obteniéndolos de la BD local de Room
                onDetailsHotelListener.onResolve(hotelRoomDataBase);
            }

            @Override
            public void onFailure(Call<Hotel> call, Throwable t) {
                Log.e(TAG, t.toString());
                onDetailsHotelListener.onReject(t);
            }
        });


    }
}
