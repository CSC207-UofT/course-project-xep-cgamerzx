package com.xepicgamerzx.hotelier.objects.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

import java.util.List;

public class HotelWithRooms {
    @Embedded
    public Hotel hotel;
    @Relation(
            parentColumn = "hotelID",
            entityColumn = "roomID"
    )
    public List<HotelRoom> hotelRooms;
}