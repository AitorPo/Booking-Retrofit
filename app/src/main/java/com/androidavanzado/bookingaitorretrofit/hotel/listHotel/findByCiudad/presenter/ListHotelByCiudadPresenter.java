package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.presenter;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.contract.ListHotelByCiudadContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.model.ListHotelByCiudadModel;

import java.util.ArrayList;

public class ListHotelByCiudadPresenter implements ListHotelByCiudadContract.Presenter {
    ListHotelByCiudadModel model;
    ListHotelByCiudadContract.View view;

    public ListHotelByCiudadPresenter(ListHotelByCiudadContract.View view){
        this.view = view;
        model = new ListHotelByCiudadModel();
    }

    @Override
    public void getHotelByCiudad(int idCIudad) {
        model.listHotelByCiudadLH(new ListHotelByCiudadContract.Model.OnListHotelByCiudadListener() {
            @Override
            public void onResolve(ArrayList<Hotel> hotelArrayList) {
                view.onSuccess(hotelArrayList);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        }, idCIudad);
    }
}
