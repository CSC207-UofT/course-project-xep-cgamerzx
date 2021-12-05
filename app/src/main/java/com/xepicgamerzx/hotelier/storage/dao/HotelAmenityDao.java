package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.relations.AmenityWithHotels;

import java.util.List;

/**
 * Data access object for HotelAmenity
 */
@Dao
public abstract class HotelAmenityDao implements BaseDao<Void, HotelAmenity> {
    /**
     * Get all HotelAmenity in HotelAmenity table.
     *
     * @return List<HotelAmenity> list of all HotelAmenity in HotelAmenity table.
     */
    @Query("SELECT * FROM HotelAmenity")
    public abstract List<HotelAmenity> getAll();

    /**
     * Get HotelAmenity with matching unique IDs in HotelAmenity table.
     *
     * @param hotelAmenityID String unique ID of hotel amenity.
     * @return List<HotelAmenity> list of all hotel amenities with unique IDs that match hotelAmenityID.
     */
    @Query("SELECT * FROM HotelAmenity WHERE uniqueId IN (:hotelAmenityID)")
    public abstract List<HotelAmenity> getIdMatch(String... hotelAmenityID);

    /**
     * Get all hotels associated with any hotel amenity.
     *
     * @return List<AmenityWithHotels> list of AmenityWithHotels entities available
     */
    @Transaction
    @Query("SELECT * FROM HotelAmenity")
    public abstract List<AmenityWithHotels> getHotelAmenitiesWithHotels();

    /**
     * Delete all HotelAmenity in HotelAmenity table.
     */
    @Query("DELETE FROM HotelAmenity")
    public abstract void deleteAll();

}
