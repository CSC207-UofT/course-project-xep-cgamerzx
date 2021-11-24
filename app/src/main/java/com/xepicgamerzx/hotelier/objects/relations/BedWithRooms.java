package com.xepicgamerzx.hotelier.objects.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomBedsCrossRef;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

import java.util.List;

public class BedWithRooms {
    @Embedded
    public Bed bed;
    @Relation(
            parentColumn = "uniqueId",
            entityColumn = "roomID",
            associateBy = @Junction(RoomBedsCrossRef.class)
    )
    public List<HotelRoom> hotelRooms;
}