package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.xepicgamerzx.hotelier.objects.BedsRoomCrossRef;
import com.xepicgamerzx.hotelier.objects.HotelAmenity;

@Dao
public interface BedRoomCrossDao extends BedDao,RoomDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBedRoomCrossRef(BedsRoomCrossRef... bedsRoomCrossRefs);
}
