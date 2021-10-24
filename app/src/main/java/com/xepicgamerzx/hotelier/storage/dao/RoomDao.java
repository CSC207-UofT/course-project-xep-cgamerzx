package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.relations.RoomWithBeds;

import java.util.List;

@Dao
public interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertRooms(HotelRoom... hotelRooms);

    @Update
    int updateRooms(HotelRoom... hotelRooms);

    @Delete
    void deleteRoom(HotelRoom hotelRoom);

    @Query("SELECT * FROM HotelRoom")
    List<HotelRoom> getAllRooms();

    @Transaction
    @Query("SELECT * FROM HotelRoom")
    List<RoomWithBeds> getRoomsWithBeds();
}
