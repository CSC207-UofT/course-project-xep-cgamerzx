package com.xepicgamerzx.hotelier.objects;

import java.util.ArrayList;

public class Hotel {

    private final String name;
    private final Address address;
    private final ArrayList<Room> rooms;
    private final HotelAmenities amenities;
    private final int starClass;

    /**
     * Create a new Hotel
     * @param name the name of this hotel
     * @param address the address of this hotel
     * @param rooms the rooms that are in this hotel
     * @param amenities the amenities included in this hotel
     * @param starClass amount of stars this hotel is
     */
    public Hotel(String name, Address address, ArrayList<Room> rooms, HotelAmenities amenities,
                 int starClass) {
        this.name = name;
        this.address = address;
        this.rooms = rooms;
        this.amenities = amenities;
        this.starClass = starClass;
    }
}
