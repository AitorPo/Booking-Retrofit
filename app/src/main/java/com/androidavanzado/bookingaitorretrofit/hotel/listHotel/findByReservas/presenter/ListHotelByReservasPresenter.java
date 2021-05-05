package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.presenter;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.data.local.HotelRepository;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.contract.ListHotelByReservasContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.model.ListHotelByReservasModel;

import java.util.ArrayList;

public class ListHotelByReservasPresenter implements ListHotelByReservasContract.Presenter {
    private ListHotelByReservasModel model;
    private ListHotelByReservasContract.View view;
    private HotelRepository repository;

    public ListHotelByReservasPresenter(ListHotelByReservasContract.View view) {
        model = new ListHotelByReservasModel();
        this.view = view;
        repository = new HotelRepository();
    }

    @Override
    public void getHotelesReservas() {
        model.getHotelesReservasLH(new ListHotelByReservasContract.Model.OnListHotelByReservasListener() {
            @Override
            public void onResolve(ArrayList<Hotel> hotelArrayList) {
                for (Hotel hotel : hotelArrayList){
                    repository.insertAll(hotel);
                }
                view.onSuccess(hotelArrayList);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        });
    }
}
