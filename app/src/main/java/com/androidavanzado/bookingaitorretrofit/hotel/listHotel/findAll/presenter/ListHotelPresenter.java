package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.presenter;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.contract.ListHotelContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.model.ListHotelModel;

import java.util.ArrayList;

public class ListHotelPresenter implements ListHotelContract.Presenter {
    private ListHotelModel listHotelModel;
    private ListHotelContract.View view;

    public ListHotelPresenter(ListHotelContract.View view){
        this.view = view;
        this.listHotelModel = new ListHotelModel();
    }

    @Override
    public void getListHotel() {
        listHotelModel.getHotelesLH(new ListHotelContract.Model.OnListHotelListener() {
            @Override
            public void onResolve(ArrayList<Hotel> hoteles) {
                view.onSuccess(hoteles);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        });
    }
}
