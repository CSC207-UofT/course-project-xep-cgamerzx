package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xepicgamerzx.hotelier.objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.relations.AmenityWithHotels;

import java.util.List;

@Dao
public interface HotelAmenityDao extends BaseDao<Void, HotelAmenity>{
    @Query("SELECT * FROM HotelAmenity")
    List<HotelAmenity> getAllHotelAmenities();

    @Query("SELECT * FROM HotelAmenity WHERE uniqueId IN (:hotelAmenityID)")
    List<HotelAmenity> getHotelAmenities(String... hotelAmenityID);

    @Transaction
    @Query("SELECT * FROM HotelAmenity")
    List<AmenityWithHotels> getHotelAmenitiesWithHotels();
}
