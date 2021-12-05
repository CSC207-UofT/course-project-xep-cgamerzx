package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

import java.util.List;
import java.util.Map;

@Dao
public abstract class HotelRoomMapDao {
    @Query("SELECT * FROM Hotel JOIN HOTELROOM ON HOTELROOM.hotelId = Hotel.hotelId" +
            " WHERE startAvailability <= :startTime AND endAvailability >= :endTime AND HOTELROOM.hotelId IN (:hotelID)")
    public abstract Map<Hotel, List<HotelRoom>> getAvailableRooms(long startTime, long endTime, long... hotelID);
}