package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity
public class RoomAmenity extends UniqueEntity{
    public RoomAmenity (@NonNull String id){
        super(id);
    }

    public RoomAmenity (RoomAmenitiesEnum roomAmenityID){
        super(roomAmenityID.toString());
    }
}
