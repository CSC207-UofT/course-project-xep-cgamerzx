package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity
public class HotelAmenity extends UniqueEntity{
    public HotelAmenity (@NonNull String uniqueId){
        super(uniqueId);
    }

    public HotelAmenity (@NonNull HotelAmenitiesEnum hotelAmenity){
        super(hotelAmenity.toString());
    }
}
