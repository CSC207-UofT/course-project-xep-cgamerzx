package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.relations.RoomWithBeds;

import java.util.List;

public interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(HotelRoom... hotelRooms);

    @Update
    void updateRooms(HotelRoom... hotelRooms);

    @Delete
    void delete(HotelRoom hotelRoom);

    @Query("SELECT * FROM HotelRoom")
    List<HotelRoom> getAll();

    @Transaction
    @Query("SELECT * FROM HotelRoom")
    List<RoomWithBeds> getRoomsWithBeds();
}
