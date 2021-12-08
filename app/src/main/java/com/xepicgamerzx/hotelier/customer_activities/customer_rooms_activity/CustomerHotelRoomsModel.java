package com.xepicgamerzx.hotelier.customer_activities.customer_rooms_activity;

import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Customer hotel room view model
 */
public class CustomerHotelRoomsModel implements Serializable {
    //private int bedsInRoomCount;
    private final int bedsCount;
    private final String bedTypes;
    private final int capacity;
    private final BigDecimal price;
    private final HotelRoom hotelRoom;
    private final String roomAvailability;

    public CustomerHotelRoomsModel(//int bedsInRoomCount,
                                   int bedCount, String bedTypes, int capacity, BigDecimal price, String roomAvail, HotelRoom hotelRoom) {
        //this.bedsInRoomCount = bedsInRoomCount;
        this.bedsCount = bedCount;
        this.bedTypes = bedTypes;
        this.capacity = capacity;
        this.price = price;
        this.hotelRoom = hotelRoom;
        this.roomAvailability = roomAvail;
    }

    //public int getHotelID() { return bedsInRoomCount; }

    public int getBedsCount() {
        return bedsCount;
    }

    public String getBedTypes() {
        return bedTypes;
    }

    public int getCapacity() {
        return capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getRoomAvailability() {
        return roomAvailability;
    }

    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }
}
