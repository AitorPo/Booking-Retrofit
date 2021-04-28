package com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.contract;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;

import java.util.ArrayList;

public interface ListHabitacionByHotelContract {
    interface Model {
        interface OnListHabitacionByHotelListener {
            void onResolve(ArrayList<Habitacion> habitacionArrayList);

            void onReject(Throwable throwable);
        }

        void getHabitacionListLH(OnListHabitacionByHotelListener onListHabitacionByHotelListener, int idHotel);
    }

    interface Presenter {
        void getHabitacionList(int idHotel);
    }

    interface View {
        void onSuccess(ArrayList<Habitacion> habitacionArrayList);

        void onFailure(Throwable throwable);
    }
}
