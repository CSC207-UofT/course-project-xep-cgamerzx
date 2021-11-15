package com.xepicgamerzx.hotelier.customer;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.HotelRoom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class CustomerHotelRoomsModel implements Serializable {
    //private int bedsInRoomCount;
    private int bedsCount;
    private String bedTypes;
    private int capacity;
    private BigDecimal price;
    private HotelRoom hotelRoom;
    private String roomAvailability;

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
