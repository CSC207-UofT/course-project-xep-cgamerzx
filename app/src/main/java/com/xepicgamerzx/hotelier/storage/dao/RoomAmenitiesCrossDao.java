package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.xepicgamerzx.hotelier.objects.RoomAmenitiesCrossRef;

@Dao
public interface RoomAmenitiesCrossDao extends RoomDao, RoomAmenityDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoomAmenitiesCrossRef(RoomAmenitiesCrossRef... roomAmenitiesCrossRefs);
}
