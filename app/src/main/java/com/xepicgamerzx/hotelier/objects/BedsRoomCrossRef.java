package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "uniqueId"})
public class BedsRoomCrossRef {
    public long roomID;
    @NonNull
    @ColumnInfo(index = true)
    public String uniqueId;

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
}
