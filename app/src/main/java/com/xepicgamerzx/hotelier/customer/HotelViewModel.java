package com.xepicgamerzx.hotelier.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class HotelViewModel implements Serializable {
    private String name;
    private String address;
    private List<BigDecimal> priceRange;
    private int numberOfRooms;
//    private String image;

    public HotelViewModel(String name, String address, List<BigDecimal> priceRange, int numberOfRooms) {
        this.name = name;
        this.address = address;
        this.priceRange = priceRange;
        this.numberOfRooms = numberOfRooms;
    }


    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public List<BigDecimal> getPriceRange() {
        return priceRange;
    }
}
