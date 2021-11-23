package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.relations.RoomWithBeds;

import java.util.List;

@Dao
public interface RoomDao extends BaseDao<List<Long>, HotelRoom>{
    @Query("SELECT * FROM HotelRoom")
    List<HotelRoom> getAll();

    @Query("SELECT * FROM HotelRoom WHERE roomID IN (:hotelRoomID)")
    List<HotelRoom> getIdMatch(Long... hotelRoomID);

    @Query("SELECT * FROM HotelRoom WHERE hotelID =:hotelID")
    List<HotelRoom> getInHotel(Long hotelID);

    @Transaction
    @Query("SELECT * FROM HotelRoom")
    List<RoomWithBeds> getRoomsWithBed();
}
