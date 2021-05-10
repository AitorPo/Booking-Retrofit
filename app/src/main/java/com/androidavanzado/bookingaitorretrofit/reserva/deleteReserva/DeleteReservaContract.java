package com.androidavanzado.bookingaitorretrofit.reserva.deleteReserva;

import com.androidavanzado.bookingaitorretrofit.beans.Reserva;
import com.androidavanzado.bookingaitorretrofit.beans.Usuario;
import com.androidavanzado.bookingaitorretrofit.reserva.reservar.ReservarContract;
import com.androidavanzado.bookingaitorretrofit.usuario.LoginContract;

public interface DeleteReservaContract {
    interface Model{
        interface onDeleteReservaListener {
            void onResolve(String message);
            void onReject(Throwable throwable);
        }

        void deleteReservaLH(onDeleteReservaListener onDeleteReservaListener, int idReserva);
    }

    interface Presenter {
        void deleteReservaPresenter(int idReserva);
    }

    interface View{
        void onSuccess(String string);
        void onFailure(Throwable throwable);
    }
}
