package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.relations.RoomWithBeds;

import java.util.List;

/**
 * Data access object for Room.
 */
@Dao
public interface RoomDao extends BaseDao<List<Long>, HotelRoom>{
    /**
     * Get all HotelRoom in HotelRoom table.
     *
     * @return List<HotelRoom> list of all HotelRoom in HotelRoom table.
     */
    @Query("SELECT * FROM HotelRoom")
    List<HotelRoom> getAll();

    /**
     * Get HotelRoom with matching IDs in HotelRoom table.
     *
     * @param hotelRoomID long ID of hotel room.
     * @return List<HotelRoom> list of all hotel rooms with IDs that match hotelRoomID.
     */
    @Query("SELECT * FROM HotelRoom WHERE roomID IN (:hotelRoomID)")
    List<HotelRoom> getIdMatch(Long... hotelRoomID);

    /**
     * Get all HotelRooms associated with a Hotel
     *
     * @param hotelID long ID of hotel associated with hotel rooms.
     * @return List<HotelRoom> list of all hotel rooms associated with hotelID.
     */
    @Query("SELECT * FROM HotelRoom WHERE hotelID =:hotelID")
    List<HotelRoom> getInHotel(Long hotelID);

    /**
     * Get all rooms associated with any bed.
     *
     * @return List<RoomWithBeds> list of RoomWithBeds entities available.
     */
    @Transaction
    @Query("SELECT * FROM HotelRoom")
    List<RoomWithBeds> getRoomsWithBed();
}
