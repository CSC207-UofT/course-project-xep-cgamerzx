package com.xepicgamerzx.hotelier.objects.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.cross_reference_objects.HotelAmenitiesCrossRef;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;

import java.util.List;

public class AmenityWithHotels {
    @Embedded
    public HotelAmenity hotelAmenity;
    @Relation(
            parentColumn = "uniqueId",
            entityColumn = "hotelID",
            associateBy = @Junction(HotelAmenitiesCrossRef.class)
    )
    public List<Hotel> hotelList;
}
