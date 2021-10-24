package com.xepicgamerzx.hotelier.objects.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;

import java.util.List;

public class HotelWithRoomsAndBeds {
    @Embedded
    public Hotel hotel;
    @Relation(
            entity = HotelRoom.class,
            parentColumn = "roomID",
            entityColumn = "hotelID"
    )
    public List<RoomWithBeds> roomWithBedsList;
}
