package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findAll.contract;

import com.androidavanzado.bookingaitorretrofit.beans.Ciudad;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

import java.util.ArrayList;

public interface ListHotelContract {
    interface Model{

            interface OnListHotelListener{
                void onResolve(ArrayList<Hotel> hoteles);
                void onReject(Throwable throwable);
            }
        void getHotelesLH(OnListHotelListener onListHotelListener);
    }

    interface Presenter{
        void getListHotel();
    }

    interface View{
        void onSuccess(ArrayList<Hotel> hoteles);
        void onFailure(Throwable throwable);
    }
}
