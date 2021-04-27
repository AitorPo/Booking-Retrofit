package com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.presenter;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.contract.ListHabitacionByPrecioDescContract;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByPrecioDesc.model.ListHabitacionByPrecioDescModel;

import java.util.ArrayList;

public class ListHabitacionByPrecioDescPresenter implements ListHabitacionByPrecioDescContract.Presenter {
    private ListHabitacionByPrecioDescModel model;
    private ListHabitacionByPrecioDescContract.View view;

    public ListHabitacionByPrecioDescPresenter(ListHabitacionByPrecioDescContract.View view){
        model = new ListHabitacionByPrecioDescModel();
        this.view = view;
    }

    @Override
    public void getHabitacionPrecioDesc() {
        model.listHabitacionPrecioDesc(new ListHabitacionByPrecioDescContract.Model.OnListPrecioDescListener() {
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
