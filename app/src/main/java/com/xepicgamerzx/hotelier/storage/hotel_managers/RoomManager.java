package com.xepicgamerzx.hotelier.storage.hotel_managers;


import android.app.Application;

import androidx.annotation.NonNull;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.RoomDao;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RoomManager implements Manager {
    private static volatile RoomManager INSTANCE;

    private final HotelierDatabase db;
    private final RoomDao roomDao;

    private RoomManager(Application application) {
        db = HotelierDatabase.getDatabase(application);
        roomDao = db.roomDao();
    }

    private RoomManager(HotelierDatabase dbInstance) {
        db = dbInstance;
        roomDao = db.roomDao();
    }

    public static RoomManager getManager(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new RoomManager(application);
        }

        return INSTANCE;
    }

    public static RoomManager getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) {
            INSTANCE = new RoomManager(dbInstance);
        }

        return INSTANCE;
    }

    /**
     * Creates HotelRoom object and inserts it to the HotelRoom database.
     *
     * @param zoneId    TimeZone of HotelRoom.
     * @param startDate Start date of availability period for the room.
     * @param endDate   End date of availability period for the room.
     * @param capacity  Occupancy capacity of the hotel room.
     * @param price     Price of the HotelRoom created.
     * @return HotelRoom object created.
     */
    @NonNull
    public HotelRoom createRoom(ZoneId zoneId, long startDate, long endDate, int capacity, BigDecimal price) {
        HotelRoom hotelRoom = new HotelRoom(
                zoneId, startDate, endDate,
                capacity,
                price);

        Long[] ids = roomDao.insert(hotelRoom).toArray(new Long[0]);
        hotelRoom = roomDao.getIdMatch(ids).get(0);

        return hotelRoom;
    }

    /**
     * Creates HotelRoom object and inserts it to the HotelRoom database.
     *
     * @param zoneId    TimeZone of HotelRoom.
     * @param startDate Start date of availability period for the room.
     * @param endDate   End date of availability period for the room.
     * @param capacity  Occupancy capacity of the hotel room.
     * @param price     Price of the HotelRoom created.
     * @return Id of HotelRoom object created.
     */
    @NonNull
    public long createRoomId(ZoneId zoneId, long startDate, long endDate, int capacity, BigDecimal price) {
        HotelRoom hotelRoom = new HotelRoom(
                zoneId, startDate, endDate,
                capacity,
                price);

        Long[] ids = roomDao.insert(hotelRoom).toArray(new Long[0]);
        hotelRoom = roomDao.getIdMatch(ids).get(0);

        return hotelRoom.roomId;
    }

    /**
     * Sets the hotelRoom's hotel association ID to the ID of hotel.
     *
     * @param hotel     Hotel the hotel room is to be associated with.
     * @param hotelRoom HotelRoom to change the hotelID of.
     */
    public void setHotelID(Hotel hotel, HotelRoom hotelRoom) {
        hotelRoom.setHotelId(hotel.hotelId);
        roomDao.update(hotelRoom);
    }

    /**
     * Sets the hotelRoom's hotel association ID to the ID of hotel.
     *
     * @param hotel     Hotel the hotel room is to be associated with.
     * @param hotelRoom HotelRoom to change the hotelID of.
     */
    public void setHotelID(Hotel hotel, long hotelRoom) {
        setHotelID(hotel, db.roomDao().getIdMatch(hotelRoom).get(0));
    }

    @Deprecated // Use getAvailableRoomsInHotel
    public List<HotelRoom> getRoomsInHotelByDate(Hotel hotel, long userStartAvail, long userEndAvail) {
        List<HotelRoom> hotelRooms = roomDao.getInHotel(hotel.hotelId);
        List<HotelRoom> filteredRooms = new ArrayList<>();
        long userStart = TimeUnit.MILLISECONDS.toDays(userStartAvail);
        long userEnd = TimeUnit.MILLISECONDS.toDays(userEndAvail);

        for (HotelRoom hotelRoom : hotelRooms) {
            long roomStartAvail = TimeUnit.MILLISECONDS.toDays(hotelRoom.getStartAvailability());
            long roomEndAvail = TimeUnit.MILLISECONDS.toDays(hotelRoom.getEndAvailability());
            System.out.println("UserStart" + userStart + "roomStart" + roomStartAvail);
            System.out.println("UserEnd" + userStart + "roomEnd" + roomStartAvail);
            if (userStart >= roomStartAvail && userEnd <= roomEndAvail) {
                filteredRooms.add(hotelRoom);
            }
        }

        return filteredRooms;
    }

    /**
     * Get all HotelRooms in a hotel that are are available within the given timeframe.
     *
     * @param startTime long UnixEpoch time start of period
     * @param endTime   long UnixEpoch time end of period
     * @param hotel     Hotel associated with hotel rooms.
     * @return List<HotelRoom> list of all hotel rooms associated with hotelID available within the given timeframe.
     */
    public List<HotelRoom> getAvailableRooms(long startTime, long endTime, Hotel hotel) {
        return getAvailableRooms(startTime, endTime, hotel.hotelId);
    }

    /**
     * Get all HotelRooms in a hotel that are are available within the given timeframe.
     *
     * @param startTime long UnixEpoch time start of period
     * @param endTime   long UnixEpoch time end of period
     * @param hotelID   Hotel associated with hotel rooms.
     * @return List<HotelRoom> list of all hotel rooms associated with hotelID available within the given timeframe.
     */
    public List<HotelRoom> getAvailableRooms(long startTime, long endTime, long hotelID) {
        return roomDao.getAvailableRooms(startTime, endTime, hotelID);
    }

    /**
     * Get all HotelRooms that are are available within the given timeframe.
     *
     * @param startTime long UnixEpoch time start of period
     * @param endTime   long UnixEpoch time end of period
     * @return List<HotelRoom> list of all hotel rooms available within the given timeframe.
     */
    public List<HotelRoom> getAvailableRooms(long startTime, long endTime) {
        return roomDao.getAvailableRooms(startTime, endTime);
    }

    public boolean isUserScheduleInHotel(Hotel hotel, long userStartAvail, long userEndAvail) {
        List<HotelRoom> hotelRooms = roomDao.getInHotel(hotel.hotelId);
        long userStart = TimeUnit.MILLISECONDS.toDays(userStartAvail);
        long userEnd = TimeUnit.MILLISECONDS.toDays(userEndAvail);

        for (HotelRoom hotelRoom : hotelRooms) {
            long roomStartAvail = TimeUnit.MILLISECONDS.toDays(hotelRoom.getStartAvailability());
            long roomEndAvail = TimeUnit.MILLISECONDS.toDays(hotelRoom.getEndAvailability());
            System.out.println("UserStart" + userStart + "roomStart" + roomStartAvail);
            System.out.println("UserEnd" + userStart + "roomEnd" + roomStartAvail);
            if (userStart >= roomStartAvail && userEnd <= roomEndAvail) {
                return true;
            }
        }

        return false;
    }

    public List<HotelRoom> getHotelRoomsInHotel(long hotelID) {
        return roomDao.getInHotel(hotelID);
    }

    public int getNumberOfRooms(long hotelID) {
        return getHotelRoomsInHotel(hotelID).size();
    }

    public int getNumberOfRooms(Hotel hotel) {
        return getNumberOfRooms(hotel.hotelId);
    }

    /**
     * Returns the min and max price of all the hotel rooms given.
     *
     * @param hotelRooms list of HotelRooms to compare between.
     * @return List<BigDecimal> min and max price of all the hotel rooms.
     */
    public List<BigDecimal> getPriceRange(List<HotelRoom> hotelRooms) {
        HotelRoom minPricedRoom = hotelRooms.stream()
                .min(Comparator.comparing(HotelRoom::getPrice))
                .get();

        HotelRoom maxPricedRoom = hotelRooms.stream()
                .max(Comparator.comparing(HotelRoom::getPrice))
                .get();

        return Arrays.asList(minPricedRoom.getPrice(), maxPricedRoom.getPrice());
    }

    /**
     * Returns the min and max price of all the hotel rooms associated with the given Hotel.
     *
     * @param hotel Hotel that rooms compared are associated with.
     * @return List<BigDecimal> min and max price of all the hotel rooms associated with the Hotel.
     */
    public List<BigDecimal> getPriceRange(Hotel hotel) {
        List<HotelRoom> hotelRooms = getHotelRoomsInHotel(hotel.hotelId);
        return getPriceRange(hotelRooms);
    }

    /**
     * Returns the min and max price of all the hotel rooms associated with the given Hotel.
     *
     * @param hotelID Long ID of the hotel that rooms compared are associated with.
     * @return List<BigDecimal> min and max price of all the hotel rooms associated with the Hotel.
     */
    public List<BigDecimal> getPriceRange(long hotelID) {
        List<HotelRoom> hotelRooms = getHotelRoomsInHotel(hotelID);
        return getPriceRange(hotelRooms);
    }

    /**
     * Closes the database if already open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }
}