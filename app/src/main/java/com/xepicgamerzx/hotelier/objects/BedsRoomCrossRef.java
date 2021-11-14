package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "uniqueId"})
public class BedsRoomCrossRef extends CrossRef{
    public long roomID;

    private int bedCount;

    public BedsRoomCrossRef(long roomID, @NonNull String uniqueId, int bedCount){
        this.roomID = roomID;
        this.uniqueId = uniqueId;
        setBedCount(bedCount);
    }

    public BedsRoomCrossRef(HotelRoom hotelRoom, Bed bed, int bedCount){
        this.roomID = hotelRoom.roomID;
        this.uniqueId = bed.getUniqueId();
        setBedCount(bedCount);
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
        if (!(o instanceof BedsRoomCrossRef)) return false;
        if (!super.equals(o)) return false;

        BedsRoomCrossRef that = (BedsRoomCrossRef) o;

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
