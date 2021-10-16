package com.xepicgamerzx.hotelier.customer;

import java.io.Serializable;

public class HotelViewModel implements Serializable {
    private String name;
    private String address;
    private String priceRange;
    private String numberOfRooms;
//    private String image;

    public HotelViewModel(String name, String address, String priceRange, String numberOfRooms) {
        this.name = name;
        this.address = address;
        this.priceRange = priceRange;
        this.numberOfRooms = numberOfRooms;

    }


    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPriceRange() {
        return priceRange;
    }
}
