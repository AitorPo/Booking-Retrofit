package com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.contract;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;

import java.util.ArrayList;

public interface ListHabitacionByPrecioAscContract {
    interface Model {
        interface OnListPrecioAscListener {
            void onResolve(ArrayList<Habitacion> habitacionArrayList);

            void onReject(Throwable throwable);
        }

        void listHabitacionPrecioAsc(OnListPrecioAscListener onListPrecioAscListener);
    }

    interface Presenter {
        void getHabitacionPrecioAsc();
    }

    interface View {
        void onSuccess(ArrayList<Habitacion> habitacionArrayList);

        void onFailure(Throwable throwable);
    }
}
