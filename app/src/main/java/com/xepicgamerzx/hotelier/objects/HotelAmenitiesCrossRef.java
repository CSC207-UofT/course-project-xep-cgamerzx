package com.xepicgamerzx.hotelier.objects;

import androidx.room.Entity;

@Entity(primaryKeys = {"hotelID", "hotelAmenityID"})
public class HotelAmenitiesCrossRef {
        public long hotelID;
        public String hotelAmenityID;
}
