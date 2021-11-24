package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xepicgamerzx.hotelier.objects.hotel_objects.RoomAmenity;
import com.xepicgamerzx.hotelier.objects.relations.AmenityWithRooms;

import java.util.List;

/**
 * Data access object for RoomAmenity.
 */
@Dao
public abstract class RoomAmenityDao implements BaseDao<Void, RoomAmenity> {
    /**
     * Get all RoomAmenity in RoomAmenity table.
     *
     * @return List<RoomAmenity> list of all RoomAmenity in RoomAmenity table.
     */
    @Query("SELECT * FROM RoomAmenity")
    public abstract List<RoomAmenity> getAll();

    /**
     * Get RoomAmenity with matching unique IDs in RoomAmenity table.
     *
     * @param roomAmenityID String unique ID of room amenity.
     * @return List<RoomAmenity> list of all room amenities with unique IDs that match roomAmenityID.
     */
    @Query("SELECT * FROM RoomAmenity WHERE uniqueId IN (:roomAmenityID)")
    public abstract List<RoomAmenity> getIdMatch(String... roomAmenityID);

    /**
     * Get all room amenities associated with any room.
     *
     * @return List<AmenityWithRooms> list of AmenityWithRooms entities available.
     */
    @Transaction
    @Query("SELECT * FROM RoomAmenity")
    public abstract List<AmenityWithRooms> getRoomAmenitiesWithRooms();
}
