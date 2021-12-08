package com.xepicgamerzx.hotelier.objects.cross_reference_objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

/**
 * Cross-reference entity for Hotel Room and Beds
 */
@Entity(primaryKeys = {"roomId", "uniqueId"}, inheritSuperIndices = true)
public class RoomBedsCrossRef extends CrossRef {
    public long roomId;
    private int bedCount;

    /**
     * Create a new RoomBedsCrossRef.
     *
     * @param roomId   the id of the room we are cross-referencing
     * @param uniqueId the unique id of the bed we are cross-referencing
     * @param bedCount the number of beds
     */
    public RoomBedsCrossRef(long roomId, @NonNull String uniqueId, int bedCount) {
        this.roomId = roomId;
        this.uniqueId = uniqueId;
        setBedCount(bedCount);
    }

    /**
     * Create a new RoomBedsCrossRef.
     *
     * @param hotelRoom the hotel room we are cross-referencing
     * @param bed       the bed we are cross-referencing
     * @param bedCount  the number of beds
     */
    public RoomBedsCrossRef(HotelRoom hotelRoom, Bed bed, int bedCount) {
        this(hotelRoom.roomId, bed.getUniqueId(), bedCount);
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomBedsCrossRef)) return false;
        if (!super.equals(o)) return false;

        RoomBedsCrossRef that = (RoomBedsCrossRef) o;

        if (roomId != that.roomId) return false;
        return getBedCount() == that.getBedCount();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (roomId ^ (roomId >>> 32));
        result = 31 * result + getBedCount();
        return result;
    }
}
