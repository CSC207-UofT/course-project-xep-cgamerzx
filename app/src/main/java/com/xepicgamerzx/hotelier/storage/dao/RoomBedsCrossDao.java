package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomBedsCrossRef;

import java.util.List;

/**
 * Data access object for BedRoomCrossRefs
 */
@Dao
public abstract class RoomBedsCrossDao implements BaseDao<Void, RoomBedsCrossRef> {
    /**
     * Get all RoomBedsCrossRef in RoomBedsCrossRef table.
     *
     * @return List<RoomBedsCrossRef> list of all RoomBedsCrossRef in RoomBedsCrossRef table.
     */
    @Query("SELECT * FROM RoomBedsCrossRef")
    public abstract List<RoomBedsCrossRef> getAll();

    /**
     * Get all room IDs in RoomBedsCrossRef table with matching bed ID.
     *
     * @param bedID String bedID referring to unique ID of bed.
     * @return List<Long> room IDs related to bedID.
     */
    @Query("SELECT roomId FROM RoomBedsCrossRef WHERE uniqueId =:bedID")
    public abstract List<Long> getWith(String bedID);

    /**
     * Get all room IDs in RoomBedsCrossRef table with matching bed ID with a min count.
     *
     * @param bedID String bedID referring to unique ID of bed.
     * @param count int minimum number of beds in cross ref.
     * @return List<Long> room IDs related to count number of bedID.
     */
    @Query("SELECT roomId FROM RoomBedsCrossRef WHERE uniqueId =:bedID AND bedCount >= :count")
    public abstract List<Long> getWith(String bedID, int count);

    /**
     * Get all bedIDs in RoomBedsCrossRef table with matching room IDs
     *
     * @param roomID long room ID.
     * @return List<String> bed IDs related to roomID
     */
    @Query("SELECT uniqueId FROM RoomBedsCrossRef WHERE roomId =:roomID")
    public abstract List<String> getWith(long roomID);

    /**
     * Get all RoomBedsCrossRefs in RoomBedsCrossRef table with matching room IDs
     *
     * @param roomID long room ID.
     * @return List<RoomBedsCrossRefs> room bed cross refs related to roomID.
     */
    @Query("SELECT * FROM RoomBedsCrossRef WHERE roomId =:roomID")
    public abstract List<RoomBedsCrossRef> getCrossWith(long roomID);

    /**
     * Get all RoomBedsCrossRefs in RoomBedsCrossRef table with matching bed IDs
     *
     * @param bedID String bedID referring to unique ID of bed.
     * @return List<RoomBedsCrossRefs> room bed cross refs related to bedID.
     */
    @Query("SELECT * FROM RoomBedsCrossRef WHERE uniqueId =:bedID")
    public abstract List<RoomBedsCrossRef> getCrossWith(String bedID);

    /**
     * Delete all RoomBedsCrossRef in RoomBedsCrossRef table.
     */
    @Query("DELETE FROM RoomBedsCrossRef")
    public abstract void deleteAll();
}
