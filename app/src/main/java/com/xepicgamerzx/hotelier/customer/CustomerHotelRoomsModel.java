package com.xepicgamerzx.hotelier.customer;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.HotelRoom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class CustomerHotelRoomsModel implements Serializable {
    //private int bedsInRoomCount;
    private List<Bed> bedsInRoom;
    private int capacity;
    private BigDecimal price;
    private HotelRoom hotelRoom;

    public CustomerHotelRoomsModel(//int bedsInRoomCount,
                                   List<Bed> bedsInRoom, int capacity, BigDecimal price, HotelRoom hotelRoom) {
        //this.bedsInRoomCount = bedsInRoomCount;
        this.bedsInRoom = bedsInRoom;
        this.capacity = capacity;
        this.price = price;
        this.hotelRoom = hotelRoom;
    }

    //public int getHotelID() { return bedsInRoomCount; }

    public List<Bed> getRelated() { return bedsInRoom; }

    public int getCapacity() {
        return capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }
}
