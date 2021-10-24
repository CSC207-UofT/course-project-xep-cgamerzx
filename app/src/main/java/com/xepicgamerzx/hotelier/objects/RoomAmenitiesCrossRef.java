package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "roomAmenityID"})
public class RoomAmenitiesCrossRef {
        @NonNull
        public long roomID;
        @NonNull
        public String roomAmenityID;

        public RoomAmenitiesCrossRef(@NonNull long roomID, @NonNull String roomAmenityID){
                this.roomID = roomID;
                this.roomAmenityID = roomAmenityID;
        }

        public RoomAmenitiesCrossRef(HotelRoom hotelRoom, RoomAmenity roomAmenity){
                this.roomID = hotelRoom.roomID;
                this.roomAmenityID = roomAmenity.getRoomAmenityID();
        }
}
