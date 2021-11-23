package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomBedsCrossRef;

import java.util.List;

/**
 * Data access object for BedRoomCrossRefs
 */
@Dao
public interface BedRoomCrossDao extends BaseDao<Void, RoomBedsCrossRef>{
    /**
     * Get all RoomBedsCrossRef in RoomBedsCrossRef table.
     *
     * @return List<RoomBedsCrossRef> list of all RoomBedsCrossRef in RoomBedsCrossRef table.
     */
    @Query("SELECT * FROM RoomBedsCrossRef")
    List<RoomBedsCrossRef> getAll();

    /**
     * Get all room IDs in RoomBedsCrossRef table with matching bed ID.
     *
     * @param bedID String bedID referring to unique ID of bed.
     * @return List<Long> room IDs related to bedID.
     */
    @Query("SELECT roomID FROM RoomBedsCrossRef WHERE uniqueId =:bedID")
    List<Long> getWith(String bedID);

    /**
     * Get all room IDs in RoomBedsCrossRef table with matching bed ID with a min count.
     *
     * @param bedID String bedID referring to unique ID of bed.
     * @param count int minimum number of beds in cross ref.
     * @return List<Long> room IDs related to count number of bedID.
     */
    @Query("SELECT roomID FROM RoomBedsCrossRef WHERE uniqueId =:bedID AND bedCount >= :count" )
    List<Long> getWith(String bedID, int count);

    /**
     * Get all bedIDs in RoomBedsCrossRef table with matching room IDs
     *
     * @param roomID long room ID.
     * @return List<String> bed IDs related to roomID
     */
    @Query("SELECT uniqueId FROM RoomBedsCrossRef WHERE roomID =:roomID")
    List<String> getWith(long roomID);

    /**
     * Get all RoomBedsCrossRefs in RoomBedsCrossRef table with matching room IDs
     *
     * @param roomID long room ID.
     * @return List<RoomBedsCrossRefs> room bed cross refs related to roomID.
     */
    @Query("SELECT * FROM RoomBedsCrossRef WHERE roomID =:roomID")
    List<RoomBedsCrossRef> getCrossWith(long roomID);

    /**
     * Get all RoomBedsCrossRefs in RoomBedsCrossRef table with matching bed IDs
     *
     * @param bedID String bedID referring to unique ID of bed.
     * @return List<RoomBedsCrossRefs> room bed cross refs related to bedID.
     */
    @Query("SELECT * FROM RoomBedsCrossRef WHERE uniqueId =:bedID")
    List<RoomBedsCrossRef> getCrossWith (String bedID);
}
