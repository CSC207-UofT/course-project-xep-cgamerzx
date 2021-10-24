package com.xepicgamerzx.hotelier.objects;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity()
public class Hotel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long hotelID;

    private String name;
    private int starClass;

    @Embedded
    private Address address;


    /**
     * Create a new Hotel
     *
     * @param name    the name of this hotel
     * @param address the address of this hotel
     *                //* @param amenities the amenities included in this hotel
     *                //* @param starClass amount of stars this hotel is
     */
    public Hotel(String name, Address address, int starClass) {
        this.name = name;
        this.address = address;
        this.starClass = starClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getStarClass() {
        return starClass;
    }

    public void setStarClass(int starClass) {
        this.starClass = starClass;
    }

    /**
     * toString Method.
     *
     * @return a string for a hotel.
     */
    @Override
    public String toString() {
        String name = String.format("Name: %s", this.name);
        String hotelAddress = String.format("\nAddress: %s", this.address.getFullStreet());

        return String.format(name + hotelAddress);
    }


}
