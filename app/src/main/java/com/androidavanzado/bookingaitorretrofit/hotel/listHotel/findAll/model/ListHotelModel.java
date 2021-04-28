package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.model;


import android.util.Log;

import androidx.lifecycle.LiveData;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.data.local.HotelRepository;
import com.androidavanzado.bookingaitorretrofit.data.network.Resource;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.contract.ListHotelContract;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HOTEL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_ALL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;

public class ListHotelModel implements ListHotelContract.Model {
    private final String TAG = "ListHotelModel";

    private final LiveData<Resource<Hotel>> resourceLiveDataHotelList;

    public ListHotelModel() {
        HotelRepository hotelRepository = new HotelRepository();
        resourceLiveDataHotelList = hotelRepository.getAllHotels();
    }

    public LiveData<Resource<Hotel>> getAllHotelsRoomDB() {
        return resourceLiveDataHotelList;
    }

    @Override
    public void getHotelesLH(final OnListHotelListener onListHotelListener) {
        ApiService apiService = ApiCLient.buildClient()
                .create(ApiService.class);
        HashMap<String, String> params = new HashMap<>();
        params.put(ACTION, HOTEL);
        params.put(QUERY, FIND_ALL);


        Call<List<Hotel>> call = apiService.getAllHotels(params);
        call.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                List<Hotel> hotels = response.body();
                Log.d(TAG, "Lista de hoteles cargada");
                onListHotelListener.onResolve((ArrayList<Hotel>) hotels);
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onListHotelListener.onReject(t);
            }
        });
    }
}


