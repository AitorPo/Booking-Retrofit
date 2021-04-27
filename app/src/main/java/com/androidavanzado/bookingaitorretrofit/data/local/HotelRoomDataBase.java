package com.androidavanzado.bookingaitorretrofit.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.androidavanzado.bookingaitorretrofit.app.MyApp;
import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

@Database(entities = {Hotel.class}, version = 4, exportSchema = false)
public abstract class HotelRoomDataBase  extends RoomDatabase {

    private static HotelRoomDataBase INSTANCE;

    public abstract HotelDAO getHotelDAO();

    public static HotelRoomDataBase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            HotelRoomDataBase.class, "booking_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
