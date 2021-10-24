package com.xepicgamerzx.hotelier.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomAmenity {
    @PrimaryKey()
    private final String roomAmenityID;

    public RoomAmenity (String roomAmenity){
        this.roomAmenityID = roomAmenity;
    }

    public RoomAmenity (RoomAmenities roomAmenity){
        this.roomAmenityID = roomAmenity.toString();
    }
}
