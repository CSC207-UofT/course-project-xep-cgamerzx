package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;

@Entity
public class HotelRoom extends NonUniqueEntity{
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

    @Override
    @NonNull
    public String toString() {
        UnixEpochDateConverter epoch = new UnixEpochDateConverter();
        String roomID = String.format(Locale.CANADA, "RoomID: %d", this.roomID);
        String schedule = String.format(Locale.CANADA, "\nSchedule: " + epoch.epochToReadable(this.getStartAvailability(), this.getEndAvailability()));
        String capacity = String.format(Locale.CANADA, "\nCapacity: %d", this.capacity);
        String price = String.format(Locale.CANADA, "\nPrice: %.2f", this.price);

        return roomID + schedule + capacity + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelRoom)) return false;
        HotelRoom hotelRoom = (HotelRoom) o;
        return roomID == hotelRoom.roomID && getHotelID() == hotelRoom.getHotelID() && getCapacity() == hotelRoom.getCapacity() && getStartAvailability() == hotelRoom.getStartAvailability() && getEndAvailability() == hotelRoom.getEndAvailability() && getPrice().equals(hotelRoom.getPrice()) && getZoneId().equals(hotelRoom.getZoneId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomID, getHotelID(), getCapacity(), getPrice(), getZoneId(), getStartAvailability(), getEndAvailability());
    }
}
