package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.relations.HotelWithAmenities;
import com.xepicgamerzx.hotelier.objects.relations.HotelWithRooms;

import java.util.List;

@Dao
public interface HotelDao extends BaseDao<List<Long>, Hotel>{
    @Query("SELECT * FROM Hotel")
    List<Hotel> getAllHotels();

    @Query("SELECT * FROM Hotel WHERE hotelID IN (:hotelID)")
    List<Hotel> getHotels(Long... hotelID);

    @Transaction
    @Query("SELECT * FROM Hotel")
    List<HotelWithRooms> getAllHotelsWithRoom();


    @Transaction
    @Query("SELECT * FROM Hotel")
    List<HotelWithAmenities> getHotelsWithAmenities();
}
