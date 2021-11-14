package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.BedsRoomCrossRef;

import java.util.List;

@Dao
public interface BedRoomCrossDao extends BaseDao<Void, BedsRoomCrossRef>{
    @Query("SELECT roomID FROM BedsRoomCrossRef WHERE id =:bedID")
    List<Long> getRoomsWithBed (String bedID);

    @Query("SELECT roomID FROM BedsRoomCrossRef WHERE id =:bedID AND bedCount >= :count" )
    List<Long> getRoomsWithBed (String bedID, int count);

    @Query("SELECT id FROM BedsRoomCrossRef WHERE roomID =:roomID")
    List<String> getBedsInRoom (long roomID);

    @Query("SELECT * FROM BedsRoomCrossRef WHERE roomID =:roomID")
    List<BedsRoomCrossRef> getBedsCrossInRoom (long roomID);
}
