package com.androidavanzado.bookingaitor.hotel.listHotel.findByPuntuacion.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByPuntuacion.contract.ListHotelByPuntuacionContract;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListHotelByPuntuacionModel implements ListHotelByPuntuacionContract.Model {


    @Override
    public void getListByPuntuacionLH(OnListHotelByPuntuacionListener onListHotelByPuntuacionListener) {

        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "HOTEL");
        param.put("QUERY", "FIND_BY_PUNTUACION");


    }

}
