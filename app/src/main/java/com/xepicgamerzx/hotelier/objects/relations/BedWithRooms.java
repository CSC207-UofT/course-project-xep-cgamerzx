package com.xepicgamerzx.hotelier.objects.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.BedsRoomCrossRef;
import com.xepicgamerzx.hotelier.objects.HotelRoom;

import java.util.List;

public class BedWithRooms {
    @Embedded
    public Bed bed;
    @Relation(
            parentColumn = "bedID",
            entityColumn = "roomID",
            associateBy = @Junction(BedsRoomCrossRef.class)
    )
    public List<HotelRoom> hotelRooms;
}