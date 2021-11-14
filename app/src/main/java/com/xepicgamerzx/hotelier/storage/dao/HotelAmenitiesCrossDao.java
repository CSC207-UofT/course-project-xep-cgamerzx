package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.HotelAmenitiesCrossRef;

import java.util.List;

@Dao
public interface HotelAmenitiesCrossDao extends BaseDao<Void, HotelAmenitiesCrossRef>{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHotelAmenitiesCrossRef(HotelAmenitiesCrossRef... hotelAmenitiesCrossRefs);

    @Query("SELECT hotelID FROM HotelAmenitiesCrossRef WHERE uniqueId =:hotelAmenityID")
    List<Long> getHotelsWithAmenity (String hotelAmenityID);

    @Query("SELECT uniqueId FROM HotelAmenitiesCrossRef WHERE hotelID =:hotelID")
    List<String> getAmenitiesInHotel (long hotelID);
}
