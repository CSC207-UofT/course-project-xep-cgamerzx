package com.xepicgamerzx.hotelier.storage.hotel_managers;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.AddressBuilder;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.storage.Manage;

import java.util.ArrayList;

/**
 * Builder for to get a hotel Id
 */
public class HotelIdBuilder {
    private String streetName;
    private String postalCode;
    private String streetNumber;
    private String city;
    private String province;
    private double latitude;
    private double longitude;

    private String name;
    private int starClass;
    private ArrayList<Long> roomIds;
    private ArrayList<String> amenityIds;

    public HotelIdBuilder setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public HotelIdBuilder setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public HotelIdBuilder setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public HotelIdBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public HotelIdBuilder setProvince(String province) {
        this.province = province;
        return this;
    }

    public HotelIdBuilder setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public HotelIdBuilder setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public HotelIdBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HotelIdBuilder setStarClass(int starClass) {
        this.starClass = starClass;
        return this;
    }

    public HotelIdBuilder setAmenityIds(ArrayList<String> amenityIds) {
        this.amenityIds = amenityIds;
        return this;
    }

    public HotelIdBuilder setRoomIds(ArrayList<Long> roomIds) {
        this.roomIds = roomIds;
        return this;
    }

    public long buildHotelId(Application application) {
        Manage manage = Manage.getManager(application);

        Address address = new AddressBuilder()
                .setStreetName(streetName)
                .setPostalCode(postalCode)
                .setStreetNumber(streetNumber)
                .setCity(city)
                .setProvince(province)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .build();
        Hotel hotel = manage.hotelManager.createHotel(name, address, starClass, roomIds, amenityIds);
        return hotel.hotelId;
    }
}
