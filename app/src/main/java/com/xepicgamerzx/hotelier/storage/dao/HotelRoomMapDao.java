package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.MapInfo;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

import java.util.List;
import java.util.Map;

@Dao
public abstract class HotelRoomMapDao {
    @MapInfo(keyColumn = "hotelId")
    @Query("SELECT * FROM HotelRoom JOIN Hotel ON HOTELROOM.hotelId = Hotel.hotelId" +
            " WHERE capacity >= :minCapacity AND " +
            "startAvailability <= :startTime AND endAvailability >= :endTime AND HOTELROOM.hotelId IN (:hotelID)")
    public abstract Map<Hotel, List<HotelRoom>> getAvailableRooms(long startTime, long endTime, int minCapacity, long... hotelID);

    @MapInfo(keyColumn = "hotelId")
    @Query("SELECT * FROM HOTELROOM JOIN Hotel ON HOTELROOM.hotelId = Hotel.hotelId" +
            " WHERE capacity >= :minCapacity AND " +
            "startAvailability <= :startTime AND endAvailability >= :endTime")
    public abstract Map<Hotel, List<HotelRoom>> getAvailableRooms(long startTime, long endTime, int minCapacity);

    /**
     * Get all hotels within a defined min capacity
     *
     * @param minCapacity int minCapacity of room
     */
    @MapInfo(keyColumn = "hotelId")
    @Query("SELECT * FROM HOTELROOM JOIN Hotel WHERE capacity >= :minCapacity")
    public abstract Map<Hotel, List<HotelRoom>> getAvailableRooms(int minCapacity);

    /**
     * Get all hotels within a defined min capacity and hotel range
     *
     * @param minCapacity int minCapacity of room
     * @param hotelID long... hotel Ids
     */
    @MapInfo(keyColumn = "hotelId")
    @Query("SELECT * FROM HotelRoom JOIN Hotel WHERE capacity >= :minCapacity " +
            "AND HOTELROOM.hotelId IN (:hotelID)")
    public abstract Map<Hotel, List<HotelRoom>> getAvailableRooms(int minCapacity, long... hotelID);

    /**
     * Get all hotels within a defined location, and capacity
     *
     * @param minCapacity int minCapacity of room
     * @param centerLonCos double latitude boundary
     * @param centerLonSin double latitude boundary
     * @param centerLatCos double longitude boundary
     * @param centerLatSin double longitude boundary
     * @param distanceCos  double cosine radius search area
     * @return List<Hotel> list of all hotel within the defined latitude and longitude areas
     */
    @MapInfo(keyColumn = "hotelId")
    @Query("SELECT * FROM HotelRoom JOIN Hotel WHERE capacity >= :minCapacity" +
            " AND :centerLatSin * latSin + :centerLatCos * latCos * (lonCos* :centerLonCos + lonSin * :centerLonSin) > :distanceCos")
    public abstract Map<Hotel, List<HotelRoom>> getAvailableRooms(int minCapacity, double centerLonCos, double centerLonSin, double centerLatCos, double centerLatSin, double distanceCos);

    /**
     * Get all hotels within a defined location, schedule, and capacity
     *
     * @param minCapacity int minCapacity of room
     * @param centerLonCos double latitude boundary
     * @param centerLonSin double latitude boundary
     * @param centerLatCos double longitude boundary
     * @param centerLatSin double longitude boundary
     * @param distanceCos  double cosine radius search area
     * @param startTime long start time
     * @param endTime long end time
     * @return List<Hotel> list of all hotel within the defined latitude and longitude areas
     */
    @MapInfo(keyColumn = "hotelId")
    @Query("SELECT * FROM HotelRoom JOIN Hotel WHERE capacity >= :minCapacity" +
            " AND :centerLatSin * latSin + :centerLatCos * latCos * (lonCos* :centerLonCos + lonSin * :centerLonSin) > :distanceCos" +
            " AND startAvailability <= :startTime AND endAvailability >= :endTime")
    public abstract Map<Hotel, List<HotelRoom>> getAvailableRooms(int minCapacity, double centerLonCos, double centerLonSin, double centerLatCos, double centerLatSin, double distanceCos, long startTime, long endTime);
}