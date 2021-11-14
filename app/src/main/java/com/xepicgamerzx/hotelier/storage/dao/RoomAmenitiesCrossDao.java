package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.RoomAmenitiesCrossRef;

import java.util.List;

@Dao
public interface RoomAmenitiesCrossDao extends BaseDao<Void, RoomAmenitiesCrossRef>{
    @Query("SELECT roomID FROM RoomAmenitiesCrossRef WHERE id =:roomAmenityID")
    List<Long> getRoomsWithAmenity (String roomAmenityID);

    @Query("SELECT id FROM RoomAmenitiesCrossRef WHERE roomID =:hotelRoomID")
    List<String> getAmenitiesInRoom (long hotelRoomID);
}
