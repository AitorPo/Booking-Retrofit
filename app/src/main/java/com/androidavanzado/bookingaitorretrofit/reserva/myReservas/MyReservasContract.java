package com.androidavanzado.bookingaitorretrofit.reserva.myReservas;

import com.androidavanzado.bookingaitorretrofit.beans.Reserva;

import java.util.ArrayList;

public interface MyReservasContract {
    interface Model{
        interface OnMyReservasListener{
            void onResolve(ArrayList<Reserva> reservas);
            void onReject(Throwable throwable);
        }

        void getMyReservasListLH(OnMyReservasListener onMyReservasListener, int idUsuario);
    }

    interface Presenter{
        void getMyReservasList(int idUsuario);
    }

    interface View{
        void onSuccess(ArrayList<Reserva> reservas);
        void onFailure(Throwable throwable);
    }
}
