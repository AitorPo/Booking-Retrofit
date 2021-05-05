package com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.presenter;

import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.contract.DetailsHotelContract;
import com.androidavanzado.bookingaitorretrofit.hotel.detailsHotel.model.DetailsHotelModel;
import com.androidavanzado.bookingaitorretrofit.data.local.HotelRepository;

public class DetailsHotelPresenter implements DetailsHotelContract.Presenter {
    private static final String TAG = "DetailsHotelPresenter";
    private DetailsHotelModel detailsHotelModel;
    private DetailsHotelContract.View view;
    private HotelRepository repository;

    public DetailsHotelPresenter(DetailsHotelContract.View view) {
        this.view = view;
        this.detailsHotelModel = new DetailsHotelModel();
        repository = new HotelRepository();
    }

    @Override
    public void getDetailsHotel(int idHotel) {
        detailsHotelModel.getHotelLS(new DetailsHotelContract.Model.OnDetailsHotelListener() {
            @Override
            public void onResolve(Hotel hotel) {
                Hotel hotelRoomDataBase = repository.getHotelDetailsRoom(idHotel);
                //Log.d(TAG, hotelRoomDataBase.toString());
                //view.onSuccess(hotelRoomDataBase);
                view.onSuccess(hotel);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        }, idHotel);
    }
}
