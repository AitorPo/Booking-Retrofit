package com.androidavanzado.bookingaitorretrofit.data.local;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.androidavanzado.bookingaitorretrofit.App;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.data.network.NetworkBoundResource;
import com.androidavanzado.bookingaitorretrofit.data.network.Resource;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.BOOKING_API_BASE_URL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_ALL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HOTEL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;

public class HotelRepository {
    private static HotelRepository hotelLab;

    private ApiService apiService;
    private HotelDAO hotelDAO;

    public HotelRepository() {

        HotelRoomDataBase hotelRoomDataBase = Room.databaseBuilder(
                App.getContext(),
                HotelRoomDataBase.class, "hotel")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        hotelDAO = hotelRoomDataBase.getHotelDAO();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BOOKING_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public LiveData<Resource<Hotel>> getAllHotels() {
        return new NetworkBoundResource<Hotel, List<Hotel>>() {

            @Override
            protected void saveCallResult(@NonNull List<Hotel> item) {
                hotelDAO.saveHotel(item);
            }

            @NonNull
            @Override
            protected LiveData<Hotel> loadFromDb() {
                return hotelDAO.getHotelsRoomDatabase();
            }

            @NonNull
            @Override
            protected Call<List<Hotel>> createCall() {
                HashMap<String, String> params = new HashMap<>();
                params.put(ACTION, HOTEL);
                params.put(QUERY, FIND_ALL);
                return apiService.getAllHotels(params);
            }
        }.getAsLiveData();
    }
}
