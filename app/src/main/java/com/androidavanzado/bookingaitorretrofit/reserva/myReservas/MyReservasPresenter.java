package com.androidavanzado.bookingaitorretrofit.reserva.myReservas;

import com.androidavanzado.bookingaitorretrofit.beans.Reserva;

import java.util.ArrayList;

public class MyReservasPresenter implements MyReservasContract.Presenter{
    private MyReservasModel model;
    private MyReservasContract.View view;


    public MyReservasPresenter(MyReservasContract.View view){
        this.view = view;
        model = new MyReservasModel();
    }

    @Override
    public void getMyReservasList(int idUsuario) {
        model.getMyReservasListLH(new MyReservasContract.Model.OnMyReservasListener() {
            @Override
            public void onResolve(ArrayList<Reserva> reservas) {
                view.onSuccess(reservas);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        }, idUsuario);
    }
}
