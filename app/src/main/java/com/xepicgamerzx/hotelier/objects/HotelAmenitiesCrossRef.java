package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"hotelID", "id"})
public class HotelAmenitiesCrossRef {
        public long hotelID;
        @NonNull
        @ColumnInfo(index = true)
        public String id;

        public HotelAmenitiesCrossRef(long hotelID, @NonNull String id){
            this.hotelID = hotelID;
            this.id = id;
        }

        public HotelAmenitiesCrossRef(Hotel hotel, HotelAmenity hotelAmenity){
                this.hotelID = hotel.hotelID;
                this.id = hotelAmenity.getId();
        }
}