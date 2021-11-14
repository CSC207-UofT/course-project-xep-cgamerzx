package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"hotelID", "uniqueId"})
public class HotelAmenitiesCrossRef {
        public long hotelID;
        @NonNull
        @ColumnInfo(index = true)
        public String uniqueId;

        public HotelAmenitiesCrossRef(long hotelID, @NonNull String uniqueId){
            this.hotelID = hotelID;
            this.uniqueId = uniqueId;
        }

        public HotelAmenitiesCrossRef(Hotel hotel, HotelAmenity hotelAmenity){
                this.hotelID = hotel.hotelID;
                this.uniqueId = hotelAmenity.getUniqueId();
        }
}