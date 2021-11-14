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

    @Query("SELECT hotelID FROM HotelAmenitiesCrossRef WHERE id =:hotelAmenityID")
    List<Long> getHotelsWithAmenity (String hotelAmenityID);

    @Query("SELECT id FROM HotelAmenitiesCrossRef WHERE hotelID =:hotelID")
    List<String> getAmenitiesInHotel (long hotelID);
}
