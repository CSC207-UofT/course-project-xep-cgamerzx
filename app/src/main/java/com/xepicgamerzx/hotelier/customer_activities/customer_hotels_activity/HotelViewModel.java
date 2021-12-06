package com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class HotelViewModel implements Serializable {
    @NonNull
    private final String name;
    private final String address;
    private final BigDecimal priceRange;
    private final int numberOfRooms;
    boolean isSelected = false;
    private Hotel hotel;
    @Nullable
    private final List<HotelRoom> rooms;

    public HotelViewModel(String name, String address, BigDecimal priceRange, int numberOfRooms, Hotel hotel, List<HotelRoom> rooms) {
        this.name = name;
        this.address = address;
        this.priceRange = priceRange;
        this.numberOfRooms = numberOfRooms;
        this.hotel = hotel;
        this.rooms = rooms;
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

    public BigDecimal getPriceRange() {
        return priceRange;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String getHotelName() {return hotel.getName();}

    public String getHotelFullStreet() {return hotel.getAddress().getFullStreet();}

    public int getHotelStar() {return hotel.getStarClass();}

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<HotelRoom> getRooms() {return rooms;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelViewModel that = (HotelViewModel) o;

        if (getNumberOfRooms() != that.getNumberOfRooms()) return false;
        if (isSelected != that.isSelected) return false;
        if (!getName().equals(that.getName())) return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null)
            return false;
        if (getPriceRange() != null ? !getPriceRange().equals(that.getPriceRange()) : that.getPriceRange() != null)
            return false;
        if (getHotel() != null ? !getHotel().equals(that.getHotel()) : that.getHotel() != null)
            return false;
        return getRooms() != null ? getRooms().equals(that.getRooms()) : that.getRooms() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getPriceRange() != null ? getPriceRange().hashCode() : 0);
        result = 31 * result + getNumberOfRooms();
        result = 31 * result + (isSelected ? 1 : 0);
        result = 31 * result + (getHotel() != null ? getHotel().hashCode() : 0);
        result = 31 * result + (getRooms() != null ? getRooms().hashCode() : 0);
        return result;
    }
}
