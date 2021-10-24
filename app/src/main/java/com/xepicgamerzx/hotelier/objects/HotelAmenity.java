package com.xepicgamerzx.hotelier.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HotelAmenity {
    @PrimaryKey()
    private final String hotelAmenityID;

    public HotelAmenity (String hotelAmenity){
        this.hotelAmenityID = hotelAmenity;
    }

    public HotelAmenity (HotelAmenities hotelAmenity){
        this.hotelAmenityID = hotelAmenity.toString();
    }
}
