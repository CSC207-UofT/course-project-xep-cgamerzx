package com.xepicgamerzx.hotelier.objects.cross_reference_objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

@Entity(primaryKeys = {"roomId", "uniqueId"}, inheritSuperIndices = true)
public class RoomBedsCrossRef extends CrossRef {
    public long roomId;
    private int bedCount;

    public RoomBedsCrossRef(long roomId, @NonNull String uniqueId, int bedCount) {
        this.roomId = roomId;
        this.uniqueId = uniqueId;
        setBedCount(bedCount);
    }

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
