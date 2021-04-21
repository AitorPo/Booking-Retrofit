package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByReservas.contract.ListHotelByReservasContract;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListHotelByReservasModel implements ListHotelByReservasContract.Model {

    @Override
    public void getHotelesReservasLH(OnListHotelByReservasListener onListHotelByReservasListener) {

        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "HOTEL");
        param.put("QUERY", "RANKING_RESERVAS");


    }
}
