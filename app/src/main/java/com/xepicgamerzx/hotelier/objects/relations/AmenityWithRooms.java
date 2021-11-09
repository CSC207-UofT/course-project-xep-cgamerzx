package com.xepicgamerzx.hotelier.objects.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.RoomAmenitiesCrossRef;
import com.xepicgamerzx.hotelier.objects.RoomAmenity;

import java.util.List;

public class AmenityWithRooms {
    @Embedded
    public RoomAmenity roomAmenity;
    @Relation(
            parentColumn = "roomAmenityID",
            entityColumn = "roomID",
            associateBy = @Junction(RoomAmenitiesCrossRef.class)
    )
    public List<HotelRoom> hotelRooms;
}
