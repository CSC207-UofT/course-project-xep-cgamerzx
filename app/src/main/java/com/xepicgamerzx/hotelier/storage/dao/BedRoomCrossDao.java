package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.RoomBedsCrossRef;

import java.util.List;

@Dao
public interface BedRoomCrossDao extends BaseDao<Void, RoomBedsCrossRef>{
    @Query("SELECT * FROM RoomBedsCrossRef")
    List<RoomBedsCrossRef> getAll();

    @Query("SELECT roomID FROM RoomBedsCrossRef WHERE uniqueId =:bedID")
    List<Long> getWith(String bedID);

    @Query("SELECT roomID FROM RoomBedsCrossRef WHERE uniqueId =:bedID AND bedCount >= :count" )
    List<Long> getWith(String bedID, int count);

    @Query("SELECT uniqueId FROM RoomBedsCrossRef WHERE roomID =:roomID")
    List<String> getWith(long roomID);

    @Query("SELECT * FROM RoomBedsCrossRef WHERE roomID =:roomID")
    List<RoomBedsCrossRef> getCrossWith(long roomID);

    @Query("SELECT * FROM RoomBedsCrossRef WHERE uniqueId =:bedID")
    List<RoomBedsCrossRef> getCrossWith (String bedID);
}
