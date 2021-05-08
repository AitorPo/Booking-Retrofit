package com.androidavanzado.bookingaitorretrofit.habitacion.reservar;

import com.androidavanzado.bookingaitorretrofit.beans.Reserva;

public interface ReservarContract {
    interface Model{
        interface onReservaListener {
            void onResolve(String message);
            void onReject(Throwable throwable);
        }

        void getReserva(onReservaListener onReservaListener, Reserva reserva);
    }

    interface Presenter {
        void doReservaPresenter(Reserva reserva);
    }

    interface View{
        void onSuccess(String string);
        void onFailure(Throwable throwable);
    }
}
