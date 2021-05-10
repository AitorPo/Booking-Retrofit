package com.androidavanzado.bookingaitorretrofit.reserva.reservar;

import com.androidavanzado.bookingaitorretrofit.beans.Reserva;

public class ReservarPresenter implements ReservarContract.Presenter{
    private ReservarContract.View view;
    private ReservarModel model;

    public ReservarPresenter(ReservarContract.View view){
        model = new ReservarModel();
        this.view = view;
    }


    @Override
    public void doReservaPresenter(Reserva reserva) {
        model.getReserva(new ReservarContract.Model.onReservaListener() {
            @Override
            public void onResolve(String message) {
                view.onSuccess(message);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        }, reserva);
    }
}
