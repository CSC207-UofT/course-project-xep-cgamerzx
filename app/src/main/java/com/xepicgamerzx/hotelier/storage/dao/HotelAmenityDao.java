package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.xepicgamerzx.hotelier.objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.relations.AmenityWithHotels;

import java.util.List;

public interface HotelAmenityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(HotelAmenity... hotelAmenities);

    @Update
    void updateHotels(HotelAmenity... hotelAmenities);

    @Delete
    void delete(HotelAmenity hotelAmenity);

    @Query("SELECT * FROM HotelAmenity")
    List<HotelAmenity> getAll();

    @Transaction
    @Query("SELECT * FROM HotelAmenity")
    List<AmenityWithHotels> getHotelAmenitiesWithHotels();
}
