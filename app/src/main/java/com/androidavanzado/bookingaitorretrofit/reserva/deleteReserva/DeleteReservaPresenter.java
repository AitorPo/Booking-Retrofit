package com.androidavanzado.bookingaitorretrofit.reserva.deleteReserva;

import com.androidavanzado.bookingaitorretrofit.beans.Reserva;

public class DeleteReservaPresenter implements DeleteReservaContract.Presenter{
    private DeleteReservaModel model;
    private DeleteReservaContract.View view;

    public DeleteReservaPresenter(DeleteReservaContract.View view){
        this.view = view;
        model = new DeleteReservaModel();
    }

    @Override
    public void deleteReservaPresenter(int idReserva) {
        model.deleteReservaLH(new DeleteReservaContract.Model.onDeleteReservaListener() {
            @Override
            public void onResolve(String message) {
                view.onSuccess(message);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        }, idReserva);
    }
}
