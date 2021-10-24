package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomAmenity {
    @NonNull
    @PrimaryKey()
    private final String roomAmenityID;

    public RoomAmenity (@NonNull String roomAmenityID){
        this.roomAmenityID = roomAmenityID;
    }

    public RoomAmenity (RoomAmenities roomAmenityID){
        this.roomAmenityID = roomAmenityID.toString();
    }

    public String getRoomAmenityID() {
        return roomAmenityID;
    }
}
