package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.BedsRoomCrossRef;
import com.xepicgamerzx.hotelier.objects.HotelAmenity;

import java.util.List;

@Dao
public interface BedRoomCrossDao extends BedDao,RoomDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBedRoomCrossRef(BedsRoomCrossRef... bedsRoomCrossRefs);

    @Query("SELECT hotelID FROM HotelAmenitiesCrossRef WHERE hotelAmenityID =:hotelAmenityID")
    List<Long> getRoomsWithBed (String hotelAmenityID);

    @Query("SELECT hotelAmenityID FROM HotelAmenitiesCrossRef WHERE hotelID =:hotelID")
    List<String> getBedsInRoom (long hotelID);
}
