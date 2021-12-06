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
public abstract class RoomDao implements BaseDao<List<Long>, HotelRoom> {
    /**
     * Get all HotelRoom in HotelRoom table.
     *
     * @return List<HotelRoom> list of all HotelRoom in HotelRoom table.
     */
    @Query("SELECT * FROM HotelRoom")
    public abstract List<HotelRoom> getAll();

    /**
     * Get HotelRoom with matching IDs in HotelRoom table.
     *
     * @param hotelRoomId long ID of hotel room.
     * @return List<HotelRoom> list of all hotel rooms with IDs that match hotelRoomId.
     */
    @Query("SELECT * FROM HotelRoom WHERE roomId IN (:hotelRoomId)")
    public abstract List<HotelRoom> getIdMatch(Long... hotelRoomId);

    /**
     * Get all HotelRooms associated with a Hotel
     *
     * @param hotelID long ID of hotel associated with hotel rooms.
     * @return List<HotelRoom> list of all hotel rooms associated with hotelID.
     */
    @Query("SELECT * FROM HotelRoom WHERE hotelId =:hotelID")
    public abstract List<HotelRoom> getInHotel(long hotelID);

    /**
     * Get all rooms associated with any bed.
     *
     * @return List<RoomWithBeds> list of RoomWithBeds entities available.
     */
    @Transaction
    @Query("SELECT * FROM HotelRoom")
    public abstract List<RoomWithBeds> getRoomsWithBed();

    /**
     * Get all HotelRooms that are are available within the given timeframe.
     *
     * @param startTime long UnixEpoch time start of period
     * @param endTime   long UnixEpoch time end of period
     * @return List<HotelRoom> list of all hotel rooms available within the given timeframe.
     */
    @Query("SELECT * FROM HOTELROOM WHERE startAvailability <= :startTime AND endAvailability >= :endTime")
    public abstract List<HotelRoom> getAvailableRooms(long startTime, long endTime);

    /**
     * Get all HotelRooms that are are available within the given timeframe.
     *
     * @param startTime   long UnixEpoch time start of period
     * @param endTime     long UnixEpoch time end of period
     * @param minCapacity int minCapacity of room
     * @return List<HotelRoom> list of all hotel rooms available within the given timeframe.
     */
    @Query("SELECT * FROM HOTELROOM WHERE startAvailability <= :startTime AND endAvailability >= :endTime AND capacity >= :minCapacity")
    public abstract List<HotelRoom> getAvailableRooms(long startTime, long endTime, int minCapacity);

    /**
     * Get all HotelRooms in a hotel that are are available within the given timeframe.
     *
     * @param hotelID   long ID(s) of hotel associated with hotel rooms.
     * @param startTime long UnixEpoch time start of period
     * @param endTime   long UnixEpoch time end of period
     * @return List<HotelRoom> list of all hotel rooms associated with hotelID available within the given timeframe.
     */
    @Query("SELECT * FROM HOTELROOM WHERE startAvailability <= :startTime AND endAvailability >= :endTime AND hotelId in (:hotelID)")
    public abstract List<HotelRoom> getAvailableRooms(long startTime, long endTime, long... hotelID);

    /**
     * Delete all HotelRooms in HotelRoom table
     */
    @Query("DELETE FROM HotelRoom")
    public abstract void deleteAll();
}
