package com.androidavanzado.bookingaitorretrofit.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

@Database(entities = {Hotel.class}, version = 5, exportSchema = false)
public abstract class HotelRoomDataBase extends RoomDatabase {

    public abstract HotelDAO getHotelDAO();
}
