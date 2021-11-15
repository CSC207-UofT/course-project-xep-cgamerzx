package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xepicgamerzx.hotelier.objects.hotel_objects.RoomAmenity;
import com.xepicgamerzx.hotelier.objects.relations.AmenityWithRooms;

import java.util.List;

@Dao
public interface RoomAmenityDao extends BaseDao<Void, RoomAmenity>{
    @Query("SELECT * FROM RoomAmenity")
    List<RoomAmenity> getAllRoomAmenities();

    @Query("SELECT * FROM RoomAmenity WHERE uniqueId IN (:roomAmenityID)")
    List<RoomAmenity> getRoomAmenities(String... roomAmenityID);

    @Transaction
    @Query("SELECT * FROM RoomAmenity")
    List<AmenityWithRooms> getRoomAmenitiesWithRooms();
}
