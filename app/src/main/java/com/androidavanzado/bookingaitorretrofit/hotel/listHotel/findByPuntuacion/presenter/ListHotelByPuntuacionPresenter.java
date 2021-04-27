package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.presenter;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.contract.ListHotelByPuntuacionContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.model.ListHotelByPuntuacionModel;

import java.util.ArrayList;

public class ListHotelByPuntuacionPresenter implements ListHotelByPuntuacionContract.Presenter {
    private ListHotelByPuntuacionModel listHotelByPuntuacionModel;
    private ListHotelByPuntuacionContract.View view;

    public ListHotelByPuntuacionPresenter(ListHotelByPuntuacionContract.View view){
        this.view = view;
        listHotelByPuntuacionModel = new ListHotelByPuntuacionModel();
    }
    @Override
    public void getListPuntuacion() {
        listHotelByPuntuacionModel.getListByPuntuacionLH(new ListHotelByPuntuacionContract.Model.OnListHotelByPuntuacionListener() {
            @Override
            public void onResolve(ArrayList<Hotel> hotelArrayList) {
                view.onSuccess(hotelArrayList);
            }
            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        });
    }
}
