package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "bedID"})
public class BedsRoomCrossRef {
    @NonNull
    public long roomID;
    @NonNull
    public String bedID;

    public BedsRoomCrossRef(@NonNull long roomID, @NonNull String bedID){
        this.roomID = roomID;
        this.bedID = bedID;
    }

    public BedsRoomCrossRef(HotelRoom hotelRoom, Bed bed){
        this.roomID = hotelRoom.roomID;
        this.bedID = bed.getBedID();
    }
}
