package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.relations.HotelWithAmenities;
import com.xepicgamerzx.hotelier.objects.relations.HotelWithRooms;

import java.util.List;

/**
 * Data access object for Hotel
 */
@Dao
public interface HotelDao extends BaseDao<List<Long>, Hotel>{
    /**
     * Get all Hotel in Hotel table.
     *
     * @return List<Hotel> list of all Hotel in Hotel table.
     */
    @Query("SELECT * FROM Hotel")
    List<Hotel> getAll();

    /**
     * Get Hotel with matching IDs in Hotel table.
     *
     * @param hotelID long ID of hotel amenity.
     * @return List<Hotel> list of all hotel with IDs that match hotelID.
     */
    @Query("SELECT * FROM Hotel WHERE hotelID IN (:hotelID)")
    List<Hotel> getIdMatch(Long... hotelID);

    /**
     * Get all hotels associated with any room.
     *
     * @return List<HotelWithRooms> list of HotelWithRooms entities available
     */
    @Transaction
    @Query("SELECT * FROM Hotel")
    List<HotelWithRooms> getHotelWithRoom();

    /**
     * Get all hotels associated with any hotel amenity.
     *
     * @return List<HotelWithAmenities> list of HotelWithAmenities entities available
     */
    @Transaction
    @Query("SELECT * FROM Hotel")
    List<HotelWithAmenities> getHotelWithAmenities();
}
