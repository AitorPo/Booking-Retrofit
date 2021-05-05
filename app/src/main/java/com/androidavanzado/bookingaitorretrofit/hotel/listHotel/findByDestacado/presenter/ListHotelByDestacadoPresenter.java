package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.presenter;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.data.local.HotelRepository;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.contract.ListHotelByDestacadoContract;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.model.ListHotelByDestacadoModel;

import java.util.ArrayList;

public class ListHotelByDestacadoPresenter implements ListHotelByDestacadoContract.Presenter {
    private ListHotelByDestacadoModel model;
    private ListHotelByDestacadoContract.View view;
    private HotelRepository hotelRepository;

    public ListHotelByDestacadoPresenter(ListHotelByDestacadoContract.View view) {
        this.view = view;
        this.model = new ListHotelByDestacadoModel();
        this.hotelRepository = new HotelRepository();
    }

    @Override
    public void getHotelesDestacados() {
        model.getDestacadosLH(new ListHotelByDestacadoContract.Model.OnListHotelByDestacadoListener() {
            @Override
            public void onResolve(ArrayList<Hotel> hotelArrayList) {
                for (Hotel hotel : hotelArrayList){
                    hotelRepository.insertAll(hotel);
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
