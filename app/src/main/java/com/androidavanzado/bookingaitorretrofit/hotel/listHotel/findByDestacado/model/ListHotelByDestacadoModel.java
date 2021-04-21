package com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.model;

import android.os.AsyncTask;
import android.util.Log;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;
import com.androidavanzado.bookingaitorretrofit.hotel.listHotel.findByDestacado.contract.ListHotelByDestacadoContract;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListHotelByDestacadoModel implements ListHotelByDestacadoContract.Model {


    @Override
    public void getDestacadosLH(final OnListHotelByDestacadoListener onListHotelByDestacadoListener) {

        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "HOTEL");
        param.put("QUERY", "FIND_BY_DESTACADO");

    }


}
