package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.relations.BedWithRooms;

import java.util.List;

/**
 * Data access object for Beds
 */
@Dao
public interface BedDao extends BaseDao<Void, Bed>{
    /**
     * Get all beds in Bed table.
     *
     * @return List<Bed> list of all beds in bed table.
     */
    @Query("SELECT * FROM Bed")
    List<Bed> getAll();

    /**
     * Get beds with matching unique IDs in bed table.
     *
     * @param bedID String unique ID of bed.
     * @return List<Bed> list of all beds with unique IDs that match bedID.
     */
    @Query("SELECT * FROM Bed WHERE uniqueId IN (:bedID)")
    List<Bed> getIdMatch(String... bedID);

    /**
     * Get all beds associated with a room.
     *
     * @return List<BedWithRooms> list of BedWithRooms objects available.
     */
    @Transaction
    @Query("SELECT * FROM Bed")
    List<BedWithRooms> getBedsWithRoom();
}
