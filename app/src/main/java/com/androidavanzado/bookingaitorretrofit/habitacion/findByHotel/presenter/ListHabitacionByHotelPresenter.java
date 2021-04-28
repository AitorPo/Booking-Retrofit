package com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.presenter;

import com.androidavanzado.bookingaitorretrofit.beans.Habitacion;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.contract.ListHabitacionByHotelContract;
import com.androidavanzado.bookingaitorretrofit.habitacion.findByHotel.model.ListHabitacionByHotelModel;

import java.util.ArrayList;

public class ListHabitacionByHotelPresenter implements ListHabitacionByHotelContract.Presenter {
    private ListHabitacionByHotelModel model;
    private ListHabitacionByHotelContract.View view;

    public ListHabitacionByHotelPresenter(ListHabitacionByHotelContract.View view) {
        model = new ListHabitacionByHotelModel();
        this.view = view;
    }

    @Override
    public void getHabitacionList(int idHotel) {
        model.getHabitacionListLH(new ListHabitacionByHotelContract.Model.OnListHabitacionByHotelListener() {
            @Override
            public void onResolve(ArrayList<Habitacion> habitacionArrayList) {
                view.onSuccess(habitacionArrayList);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        }, idHotel);
    }
}
