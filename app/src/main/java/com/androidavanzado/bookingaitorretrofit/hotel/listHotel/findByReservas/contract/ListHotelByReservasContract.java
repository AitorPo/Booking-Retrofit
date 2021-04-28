package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.contract;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

import java.util.ArrayList;

public interface ListHotelByReservasContract {
    interface Model {
        interface OnListHotelByReservasListener {
            void onResolve(ArrayList<Hotel> hotelArrayList);

            void onReject(Throwable throwable);
        }

        void getHotelesReservasLH(OnListHotelByReservasListener onListHotelByReservasListener);
    }

    interface Presenter {
        void getHotelesReservas();
    }

    interface View {
        void onSuccess(ArrayList<Hotel> hotelArrayList);

        void onFailure(Throwable throwable);
    }
}
