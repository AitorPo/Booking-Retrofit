package com.androidavanzado.bookingaitorretrofit.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.androidavanzado.bookingaitorretrofit.beans.Hotel;

import java.util.List;

@Dao
public interface HotelDAO {
    @Query("SELECT * FROM hotel")
    LiveData<Hotel> getHotelsRoomDatabase();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveHotel(List<Hotel> hotelList);

    @Query("SELECT * FROM hotel WHERE idHotel = :idHotel")
    Hotel getHotelDetailsRoom(int idHotel);
}
