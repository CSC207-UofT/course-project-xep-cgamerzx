package com.xepicgamerzx.hotelier.objects.hotel_objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;

/**
 * Hotel room entity
 */
@Entity
public class HotelRoom extends NonUniqueEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long roomId;

    private long hotelId;

    private int capacity;
    private BigDecimal price;

    private ZoneId zoneId;
    private long startAvailability;
    private long endAvailability;


    /**
     * Crates a new HotelRoom with given schedule, capacity, beds
     *
     * @param zoneId            ZoneId of the hotel room
     * @param startAvailability The first day where a hotelRoom is available
     * @param endAvailability   The last day of when a hotelRoom is available
     * @param capacity          The maximum number of people that can sleep in this HotelRoom
     * @param price             BigDecimal price of the hotel.
     */
    public HotelRoom(ZoneId zoneId, long startAvailability, long endAvailability, int capacity, BigDecimal price) {
        this.zoneId = zoneId;
        this.startAvailability = startAvailability;
        this.endAvailability = endAvailability;
        this.capacity = capacity;

        setPrice(price);
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
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
        this.price = price.setScale(2, RoundingMode.UP);
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

    /**
     * toString method
     *
     * @return a string for a hotel room
     */
    @Override
    @NonNull
    public String toString() {
        String roomId = String.format(Locale.CANADA, "RoomID: %d", this.roomId);
        String schedule = "\nSchedule: " + UnixEpochDateConverter.epochToReadable(this.getStartAvailability(), this.getEndAvailability());
        String capacity = String.format(Locale.CANADA, "\nCapacity: %d", this.capacity);
        String price = String.format(Locale.CANADA, "\nPrice: %.2f", this.price);

        return roomId + schedule + capacity + price + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelRoom)) return false;
        HotelRoom hotelRoom = (HotelRoom) o;
        return roomId == hotelRoom.roomId && getHotelId() == hotelRoom.getHotelId() && getCapacity() == hotelRoom.getCapacity() && getStartAvailability() == hotelRoom.getStartAvailability() && getEndAvailability() == hotelRoom.getEndAvailability() && getPrice().equals(hotelRoom.getPrice()) && getZoneId().equals(hotelRoom.getZoneId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, getHotelId(), getCapacity(), getPrice(), getZoneId(), getStartAvailability(), getEndAvailability());
    }
}
