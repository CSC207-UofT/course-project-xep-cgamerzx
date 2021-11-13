package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HotelAmenity {
    @NonNull
    @PrimaryKey
    private final String hotelAmenityID;

    public HotelAmenity (@NonNull String hotelAmenityID){
        this.hotelAmenityID = hotelAmenityID;
    }

    public HotelAmenity (HotelAmenitiesEnum hotelAmenity){
        this.hotelAmenityID = hotelAmenity.toString();
    }

    @NonNull
    public String getHotelAmenityID() {
        return hotelAmenityID;
    }
}
