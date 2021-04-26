package com.androidavanzado.bookingaitorretrofit.ciudad.listCiudad.contract;

import com.androidavanzado.bookingaitorretrofit.beans.Ciudad;

import java.util.ArrayList;

public interface ListCiudadContract {
    interface Model{
        interface OnListCiudadModelListener{
            void onResolve(ArrayList<Ciudad> ciudadArrayList);
            void onReject (Throwable throwable);
        }
        void getListCiudadLH(OnListCiudadModelListener onListCiudadModelListener);
    }

    interface Presenter{
        void getCiudades();
    }

    interface View{
        void onSuccess(ArrayList<Ciudad> ciudadArrayList);
        void onFailure(Throwable throwable);
    }
}
