package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"hotelID", "hotelAmenityID"})
public class HotelAmenitiesCrossRef {
        public long hotelID;
        @NonNull
        @ColumnInfo(index = true)
        public String hotelAmenityID;

        public HotelAmenitiesCrossRef(long hotelID, @NonNull String hotelAmenityID){
            this.hotelID = hotelID;
            this.hotelAmenityID = hotelAmenityID;
        }

        public HotelAmenitiesCrossRef(Hotel hotel, HotelAmenity hotelAmenity){
                this.hotelID = hotel.hotelID;
                this.hotelAmenityID = hotelAmenity.getHotelAmenityID();
        }
}