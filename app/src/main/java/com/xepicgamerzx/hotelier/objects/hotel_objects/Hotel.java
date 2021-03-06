package com.xepicgamerzx.hotelier.objects.hotel_objects;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

/**
 * Hotel entity
 */
@Entity()
public class Hotel extends NonUniqueEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long hotelId;

    private String name;
    private int starClass;

    @Embedded
    private Address address;


    /**
     * Create a new Hotel
     *
     * @param name      the name of this hotel
     * @param address   the address of this hotel
     * @param starClass amount of stars this hotel is
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
    @NonNull
    @Override
    public String toString() {
        String name = String.format("Name: %s", this.name);
        String hotelAddress = String.format("\nAddress: %s", this.address.getFullStreet());

        return name + hotelAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        Hotel hotel = (Hotel) o;
        return hotelId == hotel.hotelId && getStarClass() == hotel.getStarClass() && getName().equals(hotel.getName()) && getAddress().equals(hotel.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, getName(), getStarClass(), getAddress());
    }
}
