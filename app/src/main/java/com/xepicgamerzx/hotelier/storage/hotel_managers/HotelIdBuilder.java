package com.xepicgamerzx.hotelier.storage.hotel_managers;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.AddressBuilder;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.storage.Manage;

import java.util.ArrayList;

/**
 * Builder to get a hotel Id
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

    /**
     * Set the street name of the hotel Id.
     *
     * @param streetName the street name of the hotel Id
     * @return  HotelIdBuilder
     */
    public HotelIdBuilder setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    /**
     * Set the postal code of the hotel Id.
     *
     * @param postalCode the postal code of the hotel Id
     * @return HotelIdBuilder
     */
    public HotelIdBuilder setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * Set the street number of the hotel Id.
     *
     * @param streetNumber the street number of the hotel Id
     * @return HotelIdBuilder
     */
    public HotelIdBuilder setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    /**
     * Set the city of the hotel Id.
     *
     * @param city the city of the hotel Id
     * @return HotelIdBuilder
     */
    public HotelIdBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * Set the province of the hotel Id.
     *
     * @param province the province of the hotel Id
     * @return HotelIdBuilder
     */
    public HotelIdBuilder setProvince(String province) {
        this.province = province;
        return this;
    }

    /**
     * Set the latitude of the hotel Id.
     *
     * @param latitude Double center latitude
     * @return HotelIdBuilder
     */
    public HotelIdBuilder setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * Set the longitude of the hotel Id.
     *
     * @param longitude Double center longitude
     * @return HotelIdBuilder
     */
    public HotelIdBuilder setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * Set the name of the hotel Id.
     *
     * @param name the name of the hotel Id
     * @return HotelIdBuilder
     */
    public HotelIdBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the star class of the hotel Id.
     *
     * @param starClass the star class of the hotel Id
     * @return HotelIdBuilder
     */
    public HotelIdBuilder setStarClass(int starClass) {
        this.starClass = starClass;
        return this;
    }

    /**
     * Set the amenity Ids of the hotel Id.
     *
     * @param amenityIds the amenity Ids of the hotel Id
     * @return HotelIdBuilder
     */
    public HotelIdBuilder setAmenityIds(ArrayList<String> amenityIds) {
        this.amenityIds = amenityIds;
        return this;
    }

    /**
     * Set the room Ids of the hotel Id.
     *
     * @param roomIds the room Ids of the hotel Id
     * @return HotelIdBuilder
     */
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
