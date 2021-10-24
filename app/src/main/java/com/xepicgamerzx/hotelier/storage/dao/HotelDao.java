package com.xepicgamerzx.hotelier.storage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.relations.HotelWithAmenities;
import com.xepicgamerzx.hotelier.objects.relations.HotelWithRooms;

import java.util.List;

@Dao
public abstract class HotelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract long _insertHotelsAll(Hotel... hotels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract long _insertRoomsAll(List<HotelRoom> hotelRooms);

    @Update
    abstract void updateHotels(Hotel... hotels);

    @Delete
    abstract void delete(Hotel hotel);

    @Query("SELECT * FROM Hotel")
    public abstract List<Hotel> getAll();

    @Transaction
    @Query("SELECT * FROM Hotel")
    abstract List<HotelWithRooms> getAllHotelsWithRoom();

    @Transaction
    void insertHotelWithRooms(Hotel hotel, List<HotelRoom> hotelRooms){
        long hotelID = _insertHotelsAll(hotel);

        for (HotelRoom hotelRoom : hotelRooms){
            hotelRoom.setHotelID(hotelID);
        }
        _insertRoomsAll(hotelRooms);
    }


    @Transaction
    @Query(("SELECT * FROM Hotel WHERE Room LIKE :roomID"))
    abstract List<HotelWithRooms> getHotelsWithRoomID(int roomID);

    @Transaction
    @Query("SELECT * FROM Hotel")
    abstract List<HotelWithAmenities> getHotelsWithAmenities();
}
