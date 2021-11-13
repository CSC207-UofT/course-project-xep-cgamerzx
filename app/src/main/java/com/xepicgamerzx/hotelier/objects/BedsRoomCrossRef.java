package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "bedID"})
public class BedsRoomCrossRef {
    @NonNull
    public long roomID;
    @NonNull
    @ColumnInfo(index = true)
    public String bedID;

    private int bedCount;

    public BedsRoomCrossRef(@NonNull long roomID, @NonNull String bedID, @NonNull int bedCount){
        this.roomID = roomID;
        this.bedID = bedID;
        setBedCount(bedCount);
    }

    public BedsRoomCrossRef(HotelRoom hotelRoom, Bed bed, @NonNull int bedCount){
        this.roomID = hotelRoom.roomID;
        this.bedID = bed.getBedID();
        setBedCount(bedCount);
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }
}
