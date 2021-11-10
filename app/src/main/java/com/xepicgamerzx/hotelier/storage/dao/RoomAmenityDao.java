package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.xepicgamerzx.hotelier.objects.RoomAmenity;
import com.xepicgamerzx.hotelier.objects.relations.AmenityWithRooms;

import java.util.List;

@Dao @Deprecated
public interface RoomAmenityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRoomAmenities(RoomAmenity... roomAmenities);

    @Update
    int updateRoomAmenities(RoomAmenity... roomAmenities);

    @Delete
    void deleteRoomAmenity(RoomAmenity roomAmenities);

    @Query("SELECT * FROM RoomAmenity")
    List<RoomAmenity> getAllRoomAmenities();

    @Transaction
    @Query("SELECT * FROM RoomAmenity")
    List<AmenityWithRooms> getRoomAmenitiesWithRooms();
}
