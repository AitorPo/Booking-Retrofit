package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.data.local.HotelRepository;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.contract.ListHotelContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.model.ListHotelModel;

import java.util.ArrayList;
import java.util.List;

public class ListHotelPresenter implements ListHotelContract.Presenter {
    private static final String TAG = "ListHotelPresenter";
    private ListHotelModel listHotelModel;
    private ListHotelContract.View view;
    private HotelRepository hotelRepository;

    public ListHotelPresenter(ListHotelContract.View view) {
        this.view = view;
        this.listHotelModel = new ListHotelModel();
        this.hotelRepository = new HotelRepository();
    }

    @Override
    public void getListHotel() {
       listHotelModel.getHotelesLH(new ListHotelContract.Model.OnListHotelListener() {
            @Override
            public void onResolve(ArrayList<Hotel> hoteles) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (Hotel hotel : hoteles) {
                            hotelRepository.insertAll(hotel);
                            Log.d(TAG, hotel.toString());
                        }
                    }
                });
                List<Hotel> hotelFromRoom = hotelRepository.getHotelsRoomDatabase();
                view.onSuccess((ArrayList<Hotel>) hotelFromRoom);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        });
    }
}
