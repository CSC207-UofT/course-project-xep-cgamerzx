package com.xepicgamerzx.hotelier.objects;

import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "roomAmenityID"})
public class RoomAmenitiesCrossRef {
        public long roomID;
        public String roomAmenityID;
}
