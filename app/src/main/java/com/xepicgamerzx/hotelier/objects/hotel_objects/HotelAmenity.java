package com.xepicgamerzx.hotelier.objects.hotel_objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity
public class HotelAmenity extends UniqueEntity implements Amenity{
    public HotelAmenity(@NonNull String uniqueId) {
        super(uniqueId);
    }

    public HotelAmenity(@NonNull HotelAmenitiesEnum hotelAmenity) {
        super(hotelAmenity.toString());
    }
}
