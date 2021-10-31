package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "bedID"})
public class BedsRoomCrossRef {
    @NonNull
    public long roomID;
    @NonNull
    public String bedID;

    private int bedCount;

    public BedsRoomCrossRef(@NonNull long roomID, @NonNull String bedID){
        this.roomID = roomID;
        this.bedID = bedID;
    }

    public BedsRoomCrossRef(HotelRoom hotelRoom, Bed bed){
        this.roomID = hotelRoom.roomID;
        this.bedID = bed.getBedID();
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }
}
