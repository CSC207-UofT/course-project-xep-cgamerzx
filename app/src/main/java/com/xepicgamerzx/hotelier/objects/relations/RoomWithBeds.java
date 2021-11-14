package com.xepicgamerzx.hotelier.objects.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.RoomBedsCrossRef;
import com.xepicgamerzx.hotelier.objects.HotelRoom;

import java.util.List;

public class RoomWithBeds {
    @Embedded
    public HotelRoom hotelRoom;
    @Relation(
            parentColumn = "roomID",
            entityColumn = "uniqueId",
            associateBy = @Junction(RoomBedsCrossRef.class)
    )
    public List<Bed> beds;
}