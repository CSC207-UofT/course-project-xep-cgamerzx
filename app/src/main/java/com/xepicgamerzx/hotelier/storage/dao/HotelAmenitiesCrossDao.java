package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.cross_reference_objects.HotelAmenitiesCrossRef;

import java.util.List;

/**
 * Data access object for HotelAmenitiesCrossRef
 */
@Dao
public abstract class HotelAmenitiesCrossDao implements BaseDao<Void, HotelAmenitiesCrossRef> {
    /**
     * Get all RoomBedsCrossRef in RoomBedsCrossRef table.
     *
     * @return List<HotelAmenitiesCrossRef> list of all HotelAmenitiesCrossRef in HotelAmenitiesCrossRef table.
     */
    @Query("SELECT * FROM HotelAmenitiesCrossRef")
    public abstract List<HotelAmenitiesCrossRef> getAll();

    /**
     * Get all hotel IDs in RoomBedsCrossRef table with matching hotel amenity ID.
     *
     * @param amenityID String amenityID referring to unique ID of hotel amenity.
     * @return List<Long> hotel IDs related to amenityID.
     */
    @Query("SELECT hotelId FROM HotelAmenitiesCrossRef WHERE uniqueId =:amenityID")
    public abstract List<Long> getWith(String amenityID);

    /**
     * Get all hotel amenity IDs in RoomBedsCrossRef table with matching hotel IDs.
     *
     * @param hotelID long IDs referring to ID of the hotel.
     * @return List<String> hotel amenity unique IDs related to hotelID.
     */
    @Query("SELECT uniqueId FROM HotelAmenitiesCrossRef WHERE hotelId =:hotelID")
    public abstract List<String> getWith(long hotelID);

    /**
     * Get all HotelAmenitiesCrossRef in HotelAmenitiesCrossRef table with matching hotel IDs
     *
     * @param hotelID long ID referring to ID of the hotel.
     * @return List<HotelAmenitiesCrossRef> cross refs related to hotelID
     */
    @Query("SELECT * FROM HotelAmenitiesCrossRef WHERE hotelId =:hotelID")
    public abstract List<HotelAmenitiesCrossRef> getCrossWith(long hotelID);

    /**
     * Get all HotelAmenitiesCrossRef in HotelAmenitiesCrossRef table with matching amenity IDs
     *
     * @param amenityID string ID related to the unique ID of the hotel amenity.
     * @return List<HotelAmenitiesCrossRef> cross refs related to amenityID
     */
    @Query("SELECT * FROM HotelAmenitiesCrossRef WHERE uniqueId =:amenityID")
    public abstract List<HotelAmenitiesCrossRef> getCrossWith(String amenityID);
}
