package com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.contract;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;

import java.util.ArrayList;

public interface DetailsHabitacionContract {
    interface Model {
        interface OnDetailsHabitacionListener {
            void onResolve(Habitacion habitacion);
            void onReject(Throwable throwable);
        }
        void getHabitacionLS(OnDetailsHabitacionListener onDetailsHotelListener, int idHabitacion);
    }

    interface Presenter{
        void getDetailsHabitacion(int idHabitacion);
    }

    interface View{
        void onSuccess(Habitacion habitacion);
        void onFailure(Throwable throwable);
    }
}
