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
public abstract class HotelDao implements BaseDao<List<Long>, Hotel> {
    /**
     * Get all Hotel in Hotel table.
     *
     * @return List<Hotel> list of all Hotel in Hotel table.
     */
    @Query("SELECT * FROM Hotel")
    public abstract List<Hotel> getAll();

    /**
     * Get all HotelIds in Hotel table.
     *
     * @return List<Long> list of all Hotel Ids in Hotel table.
     */
    @Query("SELECT hotelId FROM Hotel")
    public abstract List<Long> getAllIds();

    /**
     * Get Hotel with matching IDs in Hotel table.
     *
     * @param hotelID long ID of hotel amenity.
     * @return List<Hotel> list of all hotel with IDs that match hotelID.
     */
    @Query("SELECT * FROM Hotel WHERE hotelId IN (:hotelID)")
    public abstract List<Hotel> getIdMatch(Long... hotelID);

    /**
     * Get all hotels associated with any room.
     *
     * @return List<HotelWithRooms> list of HotelWithRooms entities available
     */
    @Transaction
    @Query("SELECT * FROM Hotel")
    public abstract List<HotelWithRooms> getHotelWithRoom();

    /**
     * Get all hotels associated with any hotel amenity.
     *
     * @return List<HotelWithAmenities> list of HotelWithAmenities entities available
     */
    @Transaction
    @Query("SELECT * FROM Hotel")
    public abstract List<HotelWithAmenities> getHotelWithAmenities();

    /**
     * Get all hotels within a defined latitude and longitude area
     *
     * @param centerLonCos double latitude boundary
     * @param centerLonSin double latitude boundary
     * @param centerLatCos double longitude boundary
     * @param centerLatSin double longitude boundary
     * @param distanceCos  double cosine radius search area
     * @return List<Hotel> list of all hotel within the defined latitude and longitude areas
     */
    @Query("SELECT * FROM Hotel WHERE :centerLatSin * latSin + :centerLatCos * latCos * (lonCos* :centerLonCos + lonSin * :centerLonSin) > :distanceCos")
    public abstract List<Hotel> getHotelsInArea(double centerLonCos, double centerLonSin, double centerLatCos, double centerLatSin, double distanceCos);

    /**
     * Get all hotel Ids within a defined latitude and longitude area
     *
     * @param centerLonCos double latitude boundary
     * @param centerLonSin double latitude boundary
     * @param centerLatCos double longitude boundary
     * @param centerLatSin double longitude boundary
     * @param distanceCos  double cosine radius search area
     * @return List<Long> list of all hotel ids within the defined latitude and longitude areas
     */
    @Query("SELECT hotelId FROM Hotel WHERE :centerLatSin * latSin + :centerLatCos * latCos * (lonCos* :centerLonCos + lonSin * :centerLonSin) > :distanceCos")
    public abstract List<Long> getHotelIdsInArea(double centerLonCos, double centerLonSin, double centerLatCos, double centerLatSin, double distanceCos);

    /**
     * Delete all Hotels from a Hotel table.
     */
    @Query("DELETE FROM Hotel")
    public abstract void deleteAll();
}
