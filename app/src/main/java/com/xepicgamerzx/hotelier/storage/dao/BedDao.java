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
public abstract class BedDao implements BaseDao<Void, Bed> {
    /**
     * Get all beds in Bed table.
     *
     * @return List<Bed> list of all beds in bed table.
     */
    @Query("SELECT * FROM Bed")
    public abstract List<Bed> getAll();

    /**
     * Get beds with matching unique IDs in bed table.
     *
     * @param bedID String unique ID of bed.
     * @return List<Bed> list of all beds with unique IDs that match bedID.
     */
    @Query("SELECT * FROM Bed WHERE uniqueId IN (:bedID)")
    public abstract List<Bed> getIdMatch(String... bedID);

    /**
     * Get all beds associated with any room.
     *
     * @return List<BedWithRooms> list of BedWithRooms entities available.
     */
    @Transaction
    @Query("SELECT * FROM Bed")
    public abstract List<BedWithRooms> getBedsWithRoom();

    /**
     * Delete all Beds in Bed table.
     */
    @Query("DELETE FROM Bed")
    public abstract void deleteAll();
}
