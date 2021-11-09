package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"hotelID", "hotelAmenityID"})
public class HotelAmenitiesCrossRef {
        @NonNull
        public long hotelID;
        @NonNull
        public String hotelAmenityID;

        public HotelAmenitiesCrossRef(@NonNull long hotelID, @NonNull String hotelAmenityID){
            this.hotelID = hotelID;
            this.hotelAmenityID = hotelAmenityID;
        }

        public HotelAmenitiesCrossRef(Hotel hotel, HotelAmenity hotelAmenity){
                this.hotelID = hotel.hotelID;
                this.hotelAmenityID = hotelAmenity.getHotelAmenityID();
        }
}