package com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.presenter;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.contract.DetailsHabitacionContract;
import com.androidavanzado.bookingaitorretrofit.habitacion.detailsHabitacion.model.DetailsHabitacionModel;

public class DetailsHabitacionPresenter implements DetailsHabitacionContract.Presenter {
    private DetailsHabitacionModel model;
    private DetailsHabitacionContract.View view;

    public DetailsHabitacionPresenter(DetailsHabitacionContract.View view) {
        model = new DetailsHabitacionModel();
        this.view = view;
    }

    @Override
    public void getDetailsHabitacion(int idHabitacion) {
        model.getHabitacionLS(new DetailsHabitacionContract.Model.OnDetailsHabitacionListener() {
            @Override
            public void onResolve(Habitacion habitacion) {
                view.onSuccess(habitacion);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        }, idHabitacion);
    }
}
