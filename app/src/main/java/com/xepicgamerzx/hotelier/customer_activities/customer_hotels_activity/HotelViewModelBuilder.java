package com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity;

import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

import java.math.BigDecimal;
import java.util.List;

/**
 * Hotel view model builder
 */
public class HotelViewModelBuilder {
    private String name;
    private String address;
    private BigDecimal priceRange;
    private int numberOfRooms;
    private long hotelId;
    private double latitude;
    private double longitude;
    private int hotelStar;
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

    public HotelViewModelBuilder setHotel(long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    public HotelViewModelBuilder setRooms(List<HotelRoom> rooms) {
        this.rooms = rooms;
        return this;
    }

    public HotelViewModelBuilder setLatitude (double latitude){
        this.latitude = latitude;
        return this;
    }

    public HotelViewModelBuilder setLongitude (double longitude){
        this.longitude = longitude;
        return this;
    }

    public HotelViewModelBuilder setHotelStar (int hotelStar){
        this.hotelStar = hotelStar;
        return this;
    }

    public HotelViewModel createHotelViewModel() {
        return new HotelViewModel(name, address, priceRange, numberOfRooms, hotelId, latitude, longitude, hotelStar, rooms);
    }
}