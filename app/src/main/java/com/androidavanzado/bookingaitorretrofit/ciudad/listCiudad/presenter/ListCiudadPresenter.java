package com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.presenter;

import com.androidavanzado.bookingaitorretrofit.beans.Ciudad;
import com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.contract.ListCiudadContract;
import com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.model.ListCiudadModel;


import java.util.ArrayList;

public class ListCiudadPresenter implements ListCiudadContract.Presenter {
    private ListCiudadModel model;
    private ListCiudadContract.View view;

    public ListCiudadPresenter(ListCiudadContract.View view) {
        model = new ListCiudadModel();
        this.view = view;
    }

    @Override
    public void getCiudades() {
        model.getListCiudadLH(new ListCiudadContract.Model.OnListCiudadModelListener() {
            @Override
            public void onResolve(ArrayList<Ciudad> ciudadArrayList) {
                view.onSuccess(ciudadArrayList);
            }

            @Override
            public void onReject(Throwable throwable) {
                view.onFailure(throwable);
            }
        });
    }
}
