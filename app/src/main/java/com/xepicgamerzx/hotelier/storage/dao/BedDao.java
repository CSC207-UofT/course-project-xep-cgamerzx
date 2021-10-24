package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.relations.BedWithRooms;

import java.util.List;

public interface BedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Bed... beds);

    @Update
    void updateHotels(Bed... beds);

    @Delete
    void delete(Bed bed);

    @Query("SELECT * FROM Bed")
    List<Bed> getAll();

    @Transaction
    @Query("SELECT * FROM Bed")
    List<BedWithRooms> getBedsWithRoom();
}
