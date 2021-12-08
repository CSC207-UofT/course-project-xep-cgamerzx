package com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * View model for hotels.
 */
public class HotelViewModel implements Serializable {
    @NonNull
    private final String name;
    private final String address;
    private final BigDecimal priceRange;
    private final int numberOfRooms;
    private final double latitude;
    private final double longitude;
    @Nullable
    private final List<HotelRoom> rooms;
    private final long hotelId;
    private final int hotelStar;
    boolean isSelected = false;

    /**
     * Create a new HotelViewModel
     * @param name          name of the hotel
     * @param address       address of the hotel
     * @param priceRange    price range
     * @param numberOfRooms number of rooms in the hotel
     * @param hotelId       id of the hotel
     * @param latitude      latitude of the hotel
     * @param longitude     longitude of the hotel
     * @param hotelStar     star class of the hotel
     * @param rooms         list of hotel rooms
     */

    public HotelViewModel(@NonNull String name, String address, BigDecimal priceRange,
                          int numberOfRooms, long hotelId, double latitude, double longitude,
                          int hotelStar, @Nullable List<HotelRoom> rooms) {
        this.name = name;
        this.address = address;
        this.priceRange = priceRange;
        this.numberOfRooms = numberOfRooms;
        this.hotelId = hotelId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hotelStar = hotelStar;
        this.rooms = rooms;
    }


    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getAddress() {
        return address;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public BigDecimal getPriceRange() {
        return priceRange;
    }

    public int getHotelStar() {
        return hotelStar;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getHotelId() {
        return hotelId;
    }

    @Nullable
    public List<HotelRoom> getRooms() {
        return rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelViewModel that = (HotelViewModel) o;

        if (getNumberOfRooms() != that.getNumberOfRooms()) return false;
        if (Double.compare(that.getLatitude(), getLatitude()) != 0) return false;
        if (Double.compare(that.getLongitude(), getLongitude()) != 0) return false;
        if (isSelected != that.isSelected) return false;
        if (getHotelId() != that.getHotelId()) return false;
        if (getHotelStar() != that.getHotelStar()) return false;
        if (!getName().equals(that.getName())) return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null)
            return false;
        if (getPriceRange() != null ? !getPriceRange().equals(that.getPriceRange()) : that.getPriceRange() != null)
            return false;
        return getRooms() != null ? getRooms().equals(that.getRooms()) : that.getRooms() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName().hashCode();
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getPriceRange() != null ? getPriceRange().hashCode() : 0);
        result = 31 * result + getNumberOfRooms();
        temp = Double.doubleToLongBits(getLatitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getRooms() != null ? getRooms().hashCode() : 0);
        result = 31 * result + (isSelected ? 1 : 0);
        result = 31 * result + (int) (getHotelId() ^ (getHotelId() >>> 32));
        result = 31 * result + getHotelStar();
        return result;
    }
}
