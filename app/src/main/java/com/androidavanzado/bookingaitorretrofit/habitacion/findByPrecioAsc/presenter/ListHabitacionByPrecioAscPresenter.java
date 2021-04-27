package com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.presenter;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.contract.ListHabitacionByPrecioAscContract;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioAsc.model.ListHabitacionByPrecioAscModel;

import java.util.ArrayList;

public class ListHabitacionByPrecioAscPresenter implements ListHabitacionByPrecioAscContract.Presenter {
    private ListHabitacionByPrecioAscModel model;
    private ListHabitacionByPrecioAscContract.View view;

    public ListHabitacionByPrecioAscPresenter(ListHabitacionByPrecioAscContract.View view){
        model = new ListHabitacionByPrecioAscModel();
        this.view = view;
    }

    @Override
    public void getHabitacionPrecioAsc() {
        model.listHabitacionPrecioAsc(new ListHabitacionByPrecioAscContract.Model.OnListPrecioAscListener() {
            @Override
            public void onResolve(ArrayList<Habitacion> habitacionArrayList) {
                view.onSuccess(habitacionArrayList);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        });
    }
}
