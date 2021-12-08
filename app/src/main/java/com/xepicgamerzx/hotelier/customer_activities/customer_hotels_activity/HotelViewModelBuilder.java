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

    /**
     * Set the name of the hotel view model.
     *
     * @param name the hotel's name
     * @return current instance of HotelViewModelBuilder
     */
    public HotelViewModelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the address of the hotel view model.
     *
     * @param address the hotel's address
     * @return current instance of HotelViewModelBuilder
     */
    public HotelViewModelBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Set the price range of the hotel view model.
     *
     * @param priceRange the hotel's price range
     * @return current instance of HotelViewModelBuilder
     */
    public HotelViewModelBuilder setPriceRange(BigDecimal priceRange) {
        this.priceRange = priceRange;
        return this;
    }

    /**
     * Set the number of rooms of the hotel view model.
     *
     * @param numberOfRooms the number of rooms in the hotel
     * @return current instance of HotelViewModelBuilder
     */
    public HotelViewModelBuilder setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        return this;
    }

    /**
     * Set the hotel of the hotel view model.
     *
     * @param hotelId the hotel's id
     * @return current instance of HotelViewModelBuilder
     */
    public HotelViewModelBuilder setHotel(long hotelId) {
        this.hotelId = hotelId;
        return this;
    }

    /**
     * Set the rooms in the hotel view model.
     *
     * @param rooms the rooms in the hotel
     * @return current instance of HotelViewModelBuilder
     */
    public HotelViewModelBuilder setRooms(List<HotelRoom> rooms) {
        this.rooms = rooms;
        return this;
    }

    /**
     * Set the latitude of the hotel view model.
     *
     * @param latitude the hotel's latitude
     * @return current instance of HotelViewModelBuilder
     */
    public HotelViewModelBuilder setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * Set the longitude of the hotel view model.
     *
     * @param longitude the hotel's longitude
     * @return current instance of HotelViewModelBuilder
     */
    public HotelViewModelBuilder setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * Set the star class of the hotel view model.
     *
     * @param hotelStar number of stars the hotel has
     * @return current instance of HotelViewModelBuilder
     */
    public HotelViewModelBuilder setHotelStar(int hotelStar) {
        this.hotelStar = hotelStar;
        return this;
    }

    /**
     * Create a hotel view model.
     *
     * @return the hotel view model.
     */
    public HotelViewModel createHotelViewModel() {
        return new HotelViewModel(name, address, priceRange, numberOfRooms, hotelId, latitude,
                longitude, hotelStar, rooms);
    }
}