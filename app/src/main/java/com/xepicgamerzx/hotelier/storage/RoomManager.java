package com.xepicgamerzx.hotelier.storage;


import android.app.Application;

import androidx.annotation.NonNull;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.RoomAmenities;
import com.xepicgamerzx.hotelier.objects.RoomAmenitiesCrossRef;
import com.xepicgamerzx.hotelier.objects.RoomAmenity;
import com.xepicgamerzx.hotelier.storage.dao.RoomAmenitiesCrossDao;
import com.xepicgamerzx.hotelier.storage.dao.RoomDao;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RoomManager {
    private HotelierDatabase db;
    private RoomDao roomDao;
    private RoomAmenitiesCrossDao roomAmenitiesCrossDao;

    public RoomManager(Application application){
        db = HotelierDatabase.getDatabase(application);
        roomDao = db.roomDao();
        roomAmenitiesCrossDao = db.roomAmenitiesCrossDao();
    }

    public RoomManager(HotelierDatabase dbInstance){
        db = dbInstance;
        roomDao = db.roomDao();
        roomAmenitiesCrossDao = db.roomAmenitiesCrossDao();
    }

    @NonNull
    public HotelRoom createRoom(ZoneId zoneId, long startDate, long endDate, int capacity, BigDecimal price) {
        HotelRoom hotelRoom = new HotelRoom(
                zoneId, startDate,endDate,
                capacity,
                price);

        insertRoom(hotelRoom);

        return hotelRoom;
    }

    private long insertRoom(HotelRoom hotelRoom){
        roomDao.insertRooms(hotelRoom);
        return hotelRoom.roomID;
    }

    public void setHotelID(Hotel hotel, HotelRoom hotelRoom){
        hotelRoom.setHotelID(hotel.hotelID);
    }

    public List<HotelRoom> getHotelRooms(Long... hotelRoomID){
        return roomDao.getRooms(hotelRoomID);
    }

    public List<HotelRoom> getHotelRoomsInHotel(long hotelID){
        return roomDao.getRoomsInHotel(hotelID);
    }

    public int getNumberOfRooms(long hotelID){
        return getHotelRoomsInHotel(hotelID).size();
    }

    public List<BigDecimal> getPriceRange(List<HotelRoom> hotelRooms){
        HotelRoom minPricedRoom = hotelRooms.stream()
                .min(Comparator.comparing(HotelRoom::getPrice))
                .get();

        HotelRoom maxPricedRoom = hotelRooms.stream()
                .max(Comparator.comparing(HotelRoom::getPrice))
                .get();

        return Arrays.asList(minPricedRoom.getPrice(), maxPricedRoom.getPrice());
    }

    public List<BigDecimal> getPriceRange(Hotel hotel){
        List<HotelRoom> hotelRooms = getHotelRoomsInHotel(hotel.hotelID);
        return getPriceRange(hotelRooms);
    }

    public List<BigDecimal> getPriceRange(long hotelID){
        List<HotelRoom> hotelRooms = getHotelRoomsInHotel(hotelID);
        return getPriceRange(hotelRooms);
    }

    @NonNull
    public RoomAmenity createRoomAmenity(String amenity_name){
        RoomAmenity roomAmenity = new RoomAmenity(amenity_name);
        insertRoomAmenity(roomAmenity);
        return roomAmenity;
    }

    @NonNull
    public RoomAmenity createHotelAmenity(RoomAmenities amenity){
        RoomAmenity roomAmenity = new RoomAmenity(amenity);
        insertRoomAmenity(roomAmenity);
        return roomAmenity;
    }

    private void insertRoomAmenity(RoomAmenity roomAmenity){
        roomAmenitiesCrossDao.insertRoomAmenities(roomAmenity);
    }

    public void addAmenityToRoom(HotelRoom hotelRoom, RoomAmenity roomAmenity){
        RoomAmenitiesCrossRef roomAmenitiesCrossRef = new RoomAmenitiesCrossRef(hotelRoom, roomAmenity);
        roomAmenitiesCrossDao.insertRoomAmenitiesCrossRef(roomAmenitiesCrossRef);
    }
}