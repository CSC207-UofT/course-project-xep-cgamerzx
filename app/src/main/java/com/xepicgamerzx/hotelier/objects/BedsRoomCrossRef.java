package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "id"})
public class BedsRoomCrossRef {
    public long roomID;
    @NonNull
    @ColumnInfo(index = true)
    public String id;

    private int bedCount;

    public BedsRoomCrossRef(long roomID, @NonNull String id, int bedCount){
        this.roomID = roomID;
        this.id = id;
        setBedCount(bedCount);
    }

    public BedsRoomCrossRef(HotelRoom hotelRoom, Bed bed, int bedCount){
        this.roomID = hotelRoom.roomID;
        this.id = bed.getId();
        setBedCount(bedCount);
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }
}
