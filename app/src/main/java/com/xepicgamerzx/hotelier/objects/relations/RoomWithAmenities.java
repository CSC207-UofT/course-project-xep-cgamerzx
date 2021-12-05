package com.xepicgamerzx.hotelier.objects.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomAmenitiesCrossRef;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.hotel_objects.RoomAmenity;

import java.util.List;

public class RoomWithAmenities {
    @Embedded
    public HotelRoom hotelRoom;
    @Relation(
            parentColumn = "roomID",
            entityColumn = "uniqueId",
            associateBy = @Junction(RoomAmenitiesCrossRef.class)
    )
    public List<RoomAmenity> roomAmenityList;
}
