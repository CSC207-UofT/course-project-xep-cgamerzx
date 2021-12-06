package com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

import java.math.BigDecimal;
import java.util.List;

public class HotelViewModelBuilder {
    private String name;
    private String address;
    private BigDecimal priceRange;
    private int numberOfRooms;
    private Hotel hotel;
    private List<HotelRoom> rooms;

    public HotelViewModelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HotelViewModelBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public HotelViewModelBuilder setPriceRange(BigDecimal priceRange) {
        this.priceRange = priceRange;
        return this;
    }

    public HotelViewModelBuilder setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        return this;
    }

    public HotelViewModelBuilder setHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }

    public HotelViewModelBuilder setRooms(List<HotelRoom> rooms) {
        this.rooms = rooms;
        return this;
    }

    public HotelViewModel createHotelViewModel() {
        return new HotelViewModel(name, address, priceRange, numberOfRooms, hotel, rooms);
    }
}