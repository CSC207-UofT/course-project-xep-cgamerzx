package com.xepicgamerzx.hotelier.objects.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelAmenitiesCrossRef;
import com.xepicgamerzx.hotelier.objects.HotelAmenity;

import java.util.List;

public class AmenityWithHotels {
    @Embedded
    public HotelAmenity hotelAmenity;
    @Relation(
            parentColumn = "id",
            entityColumn = "hotelID",
            associateBy = @Junction(HotelAmenitiesCrossRef.class)
    )
    public List<Hotel> hotelList;
}
