package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.BedsRoomCrossRef;

import java.util.List;

@Dao
public interface BedRoomCrossDao extends BedDao,RoomDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBedRoomCrossRef(BedsRoomCrossRef... bedsRoomCrossRefs);

    @Query("SELECT roomID FROM BedsRoomCrossRef WHERE bedID =:bedID")
    List<Long> getRoomsWithBed (String bedID);

    @Query("SELECT roomID FROM BedsRoomCrossRef WHERE bedID =:bedID AND bedCount >= :count" )
    List<Long> getRoomsWithBed (String bedID, int count);

    @Query("SELECT bedID, bedCount FROM BedsRoomCrossRef WHERE roomID =:roomID")
    List<String> getBedsInRoom (long roomID);
}
