package com.androidavanzado.bookingaitorretrofit.data.local;

import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.App;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiCLient;
import com.androidavanzado.bookingaitorretrofit.data.service.ApiService;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.contract.ListHotelContract;
//import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.model.ListHotelModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.androidavanzado.bookingaitorretrofit.utils.Constants.ACTION;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.FIND_ALL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.HOTEL;
import static com.androidavanzado.bookingaitorretrofit.utils.Constants.QUERY;

public class HotelRepository {
    private static final String TAG = "HotelRepository";

    //private ApiService apiService;
    private HotelDAO hotelDAO;
    //private ListHotelModel model;

    public HotelRepository() {

        HotelRoomDataBase hotelRoomDataBase = HotelRoomDataBase.getInstance(App.getContext());

        hotelDAO = hotelRoomDataBase.getHotelDAO();

        //Retrofit retrofit = ApiCLient.buildClient();

        //apiService = retrofit.create(ApiService.class);

       // model = new ListHotelModel();
    }

    public void insertAll(Hotel... hotels){
        hotelDAO.insertAll(hotels);
    }

    public Hotel getHotelDetailsRoom(int idHotel){
        return hotelDAO.getHotelDetailsRoom(idHotel);
    }

    public List<Hotel> getHotelsRoomDatabase(){
        return hotelDAO.getHotelsRoomDatabase();
    }


}
