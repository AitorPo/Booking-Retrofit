package com.androidavanzado.bookingaitorretrofit.data.local;

import android.content.Context;

import androidx.room.Room;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

public class HotelLab {
    private static HotelLab hotelLab;

    private HotelDAO hotelDAO;

    private HotelLab(Context context){
        Context appContext = context.getApplicationContext();
        HotelRoomDataBase hotelRoomDataBase = Room.databaseBuilder(appContext,
                HotelRoomDataBase.class, "hotel")
                .allowMainThreadQueries()
                .build();
        hotelDAO = hotelRoomDataBase.getHotelDAO();
    }

    public static HotelLab getInstance(Context context){
        if (hotelLab == null){
            hotelLab = new HotelLab(context);
        }
        return hotelLab;
    }

    public Hotel getHotelDetails(Hotel hotel){
        return hotel;
    }
}
