package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.RoomAmenitiesCrossRef;

import java.util.List;

@Dao
public interface RoomAmenitiesCrossDao extends RoomDao, RoomAmenityDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoomAmenitiesCrossRef(RoomAmenitiesCrossRef... roomAmenitiesCrossRefs);

    @Query("SELECT roomID FROM RoomAmenitiesCrossRef WHERE roomAmenityID =:roomAmenityID")
    List<Long> getRoomsWithAmenity (String roomAmenityID);

    @Query("SELECT roomAmenityID FROM RoomAmenitiesCrossRef WHERE roomID =:hotelRoomID")
    List<String> getAmenitiesInRoom (long hotelRoomID);
}
