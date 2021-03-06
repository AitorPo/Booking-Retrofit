package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.contract;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

import java.util.ArrayList;

public interface ListHotelByDestacadoContract {
    interface Model {
        interface OnListHotelByDestacadoListener {
            void onResolve(ArrayList<Hotel> hotelArrayList);

            void onReject(Throwable throwable);
        }

        void getDestacadosLH(OnListHotelByDestacadoListener onListHotelByDestacadoListener);
    }

    interface Presenter {
        void getHotelesDestacados();
    }

    interface View {
        void onSuccess(ArrayList<Hotel> hotelArrayList);

        void onFailure(Throwable throwable);
    }
}
