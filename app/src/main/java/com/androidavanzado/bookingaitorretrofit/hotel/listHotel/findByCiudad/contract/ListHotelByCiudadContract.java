package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByCiudad.contract;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

import java.util.ArrayList;

public interface ListHotelByCiudadContract {
    interface Model {
        interface OnListHotelByCiudadListener{
            void onResolve(ArrayList<Hotel> hotelArrayList);
            void onReject(Throwable throwable);
        }
        void listHotelByCiudadLH(OnListHotelByCiudadListener onListHotelByCiudadListener, int idCiudad);
    }

    interface Presenter{
        void getHotelByCiudad(int idCIudad);
    }

    interface View{
        void onSuccess(ArrayList<Hotel> hotelArrayList);
        void onFailure(Throwable throwable);
    }
}
