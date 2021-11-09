package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.relations.BedWithRooms;

import java.util.List;

@Dao
public interface BedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertBeds(Bed... beds);

    @Update
    int updateBed(Bed... beds);

    @Delete
    void deleteBed(Bed bed);

    @Query("SELECT * FROM Bed")
    List<Bed> getAllBeds();

    @Query("SELECT * FROM Bed WHERE bedID IN (:bedID)")
    List<Bed> getBeds(String... bedID);

    @Transaction
    @Query("SELECT * FROM Bed")
    List<BedWithRooms> getBedsWithRoom();
}
