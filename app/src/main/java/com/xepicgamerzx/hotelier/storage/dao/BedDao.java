package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.relations.BedWithRooms;

import java.util.List;

@Dao
public interface BedDao extends BaseDao<Void, Bed>{
    @Query("SELECT * FROM Bed")
    List<Bed> getAllBeds();

    @Query("SELECT * FROM Bed WHERE id IN (:bedID)")
    List<Bed> getBeds(String... bedID);

    @Transaction
    @Query("SELECT * FROM Bed")
    List<BedWithRooms> getBedsWithRoom();
}
