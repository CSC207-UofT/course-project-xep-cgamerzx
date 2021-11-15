package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.RoomAmenitiesCrossRef;

import java.util.List;

@Dao
public interface RoomAmenitiesCrossDao extends BaseDao<Void, RoomAmenitiesCrossRef>{
    @Query("SELECT * FROM RoomAmenitiesCrossRef")
    List<RoomAmenitiesCrossRef> getAll();

    @Query("SELECT roomID FROM RoomAmenitiesCrossRef WHERE uniqueId =:roomAmenityID")
    List<Long> getWith(String roomAmenityID);

    @Query("SELECT uniqueId FROM RoomAmenitiesCrossRef WHERE roomID =:roomID")
    List<String> getWith(long roomID);

    @Query("SELECT * FROM RoomAmenitiesCrossRef WHERE roomID =:roomID")
    List<RoomAmenitiesCrossRef> getCrossWith(long roomID);

    @Query("SELECT * FROM RoomAmenitiesCrossRef WHERE uniqueId =:roomAmenityID")
    List<RoomAmenitiesCrossRef> getCrossWith (String roomAmenityID);
}
