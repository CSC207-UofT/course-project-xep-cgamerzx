package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.xepicgamerzx.hotelier.objects.BedsRoomCrossRef;
import com.xepicgamerzx.hotelier.objects.HotelAmenitiesCrossRef;

@Dao
public interface HotelAmenitiesCrossDao extends HotelDao, HotelAmenityDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHotelAmenitiesCrossRef(HotelAmenitiesCrossRef... hotelAmenitiesCrossRefs);
}
