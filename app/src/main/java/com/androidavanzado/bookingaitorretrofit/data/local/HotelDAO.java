package com.androidavanzado.bookingaitorretrofit.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

import java.util.List;

@Dao
public interface HotelDAO {
    // Llamamos a la tabla 'h' para hacer más evidente la distinción a la hora de obtener los datos
    @Query("SELECT * FROM h")
    List<Hotel> getHotelsRoomDatabase();

    @Query("SELECT * FROM h WHERE idHotel = :idHotel")
    Hotel getHotelDetailsRoom(int idHotel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Hotel... hotels);
}
