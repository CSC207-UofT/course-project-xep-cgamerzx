package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.HotelAmenitiesCrossRef;

import java.util.List;

@Dao
public interface HotelAmenitiesCrossDao extends BaseDao<Void, HotelAmenitiesCrossRef>{
    @Query("SELECT * FROM HotelAmenitiesCrossRef")
    List<HotelAmenitiesCrossRef> getAll();

    @Query("SELECT hotelID FROM HotelAmenitiesCrossRef WHERE uniqueId =:bedID")
    List<Long> getWith(String bedID);

    @Query("SELECT uniqueId FROM HotelAmenitiesCrossRef WHERE hotelID =:hotelID")
    List<String> getWith(long hotelID);

    @Query("SELECT * FROM HotelAmenitiesCrossRef WHERE hotelID =:hotelID")
    List<HotelAmenitiesCrossRef> getCrossWith(long hotelID);

    @Query("SELECT * FROM HotelAmenitiesCrossRef WHERE uniqueId =:amenityID")
    List<HotelAmenitiesCrossRef> getCrossWith (String amenityID);
}
