package com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.contract;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;

import java.util.ArrayList;

public interface ListHabitacionByPrecioDescContract {
    interface Model {
        interface OnListPrecioDescListener {
            void onResolve(ArrayList<Habitacion> habitacionArrayList);

            void onReject(Throwable throwable);
        }

        void listHabitacionPrecioDesc(OnListPrecioDescListener onListPrecioDescListener);
    }

    interface Presenter {
        void getHabitacionPrecioDesc();
    }

    interface View {
        void onSuccess(ArrayList<Habitacion> habitacionArrayList);

        void onFailure(Throwable throwable);
    }
}
