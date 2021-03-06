package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.contract;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

import java.util.ArrayList;

public interface ListHotelByPuntuacionContract {
    interface Model {
        interface OnListHotelByPuntuacionListener {
            void onResolve(ArrayList<Hotel> hotelArrayList);

            void onReject(Throwable throwable);
        }

        void getListByPuntuacionLH(OnListHotelByPuntuacionListener onListHotelByPuntuacionListener);
    }

    interface Presenter {
        void getListPuntuacion();
    }

    interface View {
        void onSuccess(ArrayList<Hotel> hotelArrayList);

        void onFailure(Throwable throwable);
    }
}
