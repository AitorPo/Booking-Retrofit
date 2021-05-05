package com.androidavanzado.bookingaitorretrofit.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Hotel.class}, version = 13, exportSchema = false)
public abstract class HotelRoomDataBase extends RoomDatabase {
    public abstract HotelDAO getHotelDAO();

    private static volatile HotelRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    public static final ExecutorService dbWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static HotelRoomDataBase getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (HotelRoomDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HotelRoomDataBase.class, "hotel_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
