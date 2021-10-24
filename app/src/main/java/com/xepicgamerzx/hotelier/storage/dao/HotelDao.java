package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.relations.HotelWithAmenities;
import com.xepicgamerzx.hotelier.objects.relations.HotelWithRooms;

import java.util.List;

@Dao
public interface HotelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertHotels(Hotel... hotels);

    @Update
    int updateHotels(Hotel... hotels);

    @Delete
    void deleteHotel(Hotel hotel);

    @Query("SELECT * FROM Hotel")
    List<Hotel> getAllHotels();

    @Transaction
    @Query("SELECT * FROM Hotel")
    List<HotelWithRooms> getAllHotelsWithRoom();

    @Transaction
    @Query("SELECT * FROM Hotel")
    List<HotelWithAmenities> getHotelsWithAmenities();
}
