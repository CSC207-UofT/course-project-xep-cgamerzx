package com.xepicgamerzx.hotelier.storage;


import androidx.annotation.NonNull;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;

import java.time.ZoneId;

public class RoomManager {
    @NonNull
    public HotelRoom createRoom(ZoneId zoneId, long startDate, long endDate, int capacity, long price) {
        HotelRoom hotelRoom = new HotelRoom(
                zoneId, startDate,endDate,
                capacity,
                price);
        return hotelRoom;
    }

    public void setHotelID(Hotel hotel, HotelRoom hotelRoom){
        hotelRoom.setHotelID(hotel.hotelID);
    }
}

