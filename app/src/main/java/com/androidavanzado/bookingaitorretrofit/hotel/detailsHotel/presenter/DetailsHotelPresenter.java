package com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.presenter;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.contract.DetailsHotelContract;
import com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.model.DetailsHotelModel;

import java.util.ArrayList;

public class DetailsHotelPresenter implements DetailsHotelContract.Presenter {
    private DetailsHotelModel detailsHotelModel;
    private DetailsHotelContract.View view;

    public DetailsHotelPresenter(DetailsHotelContract.View view) {
        this.view = view;
        this.detailsHotelModel = new DetailsHotelModel();
    }

    @Override
    public void getDetailsHotel(int idHotel) {
        detailsHotelModel.getHotelLS(new DetailsHotelContract.Model.OnDetailsHotelListener() {
            @Override
            public void onResolve(Hotel hotel) {

                view.onSuccess(hotel);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        }, idHotel);
    }
}
