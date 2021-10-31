package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Locale;

@Entity
public class HotelRoom{
    @PrimaryKey(autoGenerate = true)
    public long roomID;

    private long hotelID;

    private int capacity;
    private BigDecimal price;

    private ZoneId zoneId;
    private long startAvailability;
    private long endAvailability;


    /**
     * Crates a new HotelRoom with given schedule, capacity, beds
     *
     * @param startAvailability the first day where a hotelRoom is available
     * @param endAvailability   the last day of when a hotelRoom is available
     * @param capacity          The maximum number of people that can sleep in this HotelRoom
     *                          //* @param hotel The hotel that this hotelRoom is apart of
     *                          //* @param amenities The amenities that this hotelRoom has
     */
    public HotelRoom(ZoneId zoneId, long startAvailability, long endAvailability, int capacity, BigDecimal price) {
        this.zoneId = zoneId;
        this.startAvailability = startAvailability;
        this.endAvailability = endAvailability;
        this.capacity = capacity;
        this.price = price;
    }

    public long getHotelID() {
        return hotelID;
    }

    public void setHotelID(long hotelID) {
        this.hotelID = hotelID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public long getStartAvailability() {
        return startAvailability;
    }

    public void setStartAvailability(long startAvailability) {
        this.startAvailability = startAvailability;
    }

    public long getEndAvailability() {
        return endAvailability;
    }

    public void setEndAvailability(long endAvailability) {
        this.endAvailability = endAvailability;
    }

    @Override
    @NonNull
    public String toString() {
        return String.format(Locale.CANADA, "Schedule: (%s, %s) \nCapacity: %d \nBeds: %s \nPrice: %d",
                this.getStartAvailability(), this.getEndAvailability(), this.capacity, this.price);
    }

}
