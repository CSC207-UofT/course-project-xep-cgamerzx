package com.xepicgamerzx.hotelier.objects.hotel_objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomAmenitiesEnum;

@Entity
public class RoomAmenity extends UniqueEntity implements Amenity {
    public RoomAmenity(@NonNull String uniqueId) {
        super(uniqueId);
    }

    public RoomAmenity(RoomAmenitiesEnum roomAmenityID) {
        super(roomAmenityID.toString());
    }
}
