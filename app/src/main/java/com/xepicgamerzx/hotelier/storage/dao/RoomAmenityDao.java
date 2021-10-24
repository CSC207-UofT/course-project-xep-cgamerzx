package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.xepicgamerzx.hotelier.objects.RoomAmenity;
import com.xepicgamerzx.hotelier.objects.relations.AmenityWithRooms;

import java.util.List;

public interface RoomAmenityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(RoomAmenity... roomAmenities);

    @Update
    void updateHotels(RoomAmenity... roomAmenities);

    @Delete
    void delete(RoomAmenity roomAmenities);

    @Query("SELECT * FROM RoomAmenity")
    List<RoomAmenity> getAll();

    @Transaction
    @Query("SELECT * FROM RoomAmenity")
    List<AmenityWithRooms> getRoomAmenitiesWithRooms();
}
