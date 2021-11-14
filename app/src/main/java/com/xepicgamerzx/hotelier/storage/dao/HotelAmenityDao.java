package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.xepicgamerzx.hotelier.objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.relations.AmenityWithHotels;

import java.util.List;

@Dao
public interface HotelAmenityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHotelAmenities(HotelAmenity... hotelAmenities);

    @Update
    int updateHotelAmenities(HotelAmenity... hotelAmenities);

    @Delete
    void deleteHotelAmenity(HotelAmenity hotelAmenity);

    @Query("SELECT * FROM HotelAmenity")
    List<HotelAmenity> getAllHotelAmenities();

    @Query("SELECT * FROM HotelAmenity WHERE id IN (:hotelAmenityID)")
    List<HotelAmenity> getHotelAmenities(String... hotelAmenityID);

    @Transaction
    @Query("SELECT * FROM HotelAmenity")
    List<AmenityWithHotels> getHotelAmenitiesWithHotels();
}
