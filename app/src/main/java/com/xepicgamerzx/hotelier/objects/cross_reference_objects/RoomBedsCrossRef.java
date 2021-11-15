package com.xepicgamerzx.hotelier.objects.cross_reference_objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

@Entity(primaryKeys = {"roomID", "uniqueId"}, inheritSuperIndices = true)
public class RoomBedsCrossRef extends CrossRef {
    public long roomID;
    private int bedCount;

    public RoomBedsCrossRef(long roomID, @NonNull String uniqueId, int bedCount){
        this.roomID = roomID;
        this.uniqueId = uniqueId;
        setBedCount(bedCount);
    }

    public RoomBedsCrossRef(HotelRoom hotelRoom, Bed bed, int bedCount){
        this(hotelRoom.roomID, bed.getUniqueId(), bedCount);
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

        if (roomID != that.roomID) return false;
        return getBedCount() == that.getBedCount();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (roomID ^ (roomID >>> 32));
        result = 31 * result + getBedCount();
        return result;
    }
}
