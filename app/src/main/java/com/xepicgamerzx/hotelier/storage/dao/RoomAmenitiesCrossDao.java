package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomAmenitiesCrossRef;

import java.util.List;

/**
 * Data access object for RoomAmenitiesCrossRef.
 */
@Dao
public abstract class RoomAmenitiesCrossDao implements BaseDao<Void, RoomAmenitiesCrossRef> {
    /**
     * Get all RoomAmenitiesCrossRef in RoomAmenitiesCrossRef table.
     *
     * @return List<RoomAmenitiesCrossRef> list of all RoomAmenitiesCrossRef in RoomAmenitiesCrossRef table.
     */
    @Query("SELECT * FROM RoomAmenitiesCrossRef")
    public abstract List<RoomAmenitiesCrossRef> getAll();

    /**
     * Get all room IDs in RoomAmenitiesCrossRef table with matching room amenity ID.
     *
     * @param roomAmenityID String amenityID referring to unique ID of room amenity.
     * @return List<Long> room IDs related to roomAmenityID.
     */
    @Query("SELECT roomID FROM RoomAmenitiesCrossRef WHERE uniqueId =:roomAmenityID")
    public abstract List<Long> getWith(String roomAmenityID);

    /**
     * Get all room amenity IDs in RoomAmenitiesCrossRef table with matching room ID.
     *
     * @param roomID long roomID referring to the ID of the room.
     * @return List<String> unique room amenity IDs related to roomID.
     */
    @Query("SELECT uniqueId FROM RoomAmenitiesCrossRef WHERE roomID =:roomID")
    public abstract List<String> getWith(long roomID);

    /**
     * Get all RoomAmenitiesCrossRef in RoomAmenitiesCrossRef table with matching room IDs.
     *
     * @param roomID long room ID.
     * @return List<RoomAmenitiesCrossRef> room room amenity cross refs related to roomID.
     */
    @Query("SELECT * FROM RoomAmenitiesCrossRef WHERE roomID =:roomID")
    public abstract List<RoomAmenitiesCrossRef> getCrossWith(long roomID);

    /**
     * Get all RoomAmenitiesCrossRef in RoomAmenitiesCrossRef table with matching roomAmenity IDs.
     *
     * @param roomAmenityID String unique room amenity ID.
     * @return List<RoomAmenitiesCrossRef> room room amenity cross refs related to roomAmenityID.
     */
    @Query("SELECT * FROM RoomAmenitiesCrossRef WHERE uniqueId =:roomAmenityID")
    public abstract List<RoomAmenitiesCrossRef> getCrossWith(String roomAmenityID);
}
