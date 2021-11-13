package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "roomAmenityID"})
public class RoomAmenitiesCrossRef {
        public long roomID;
        @NonNull
        @ColumnInfo(index = true)
        public String roomAmenityID;

        public RoomAmenitiesCrossRef(long roomID, @NonNull String roomAmenityID){
                this.roomID = roomID;
                this.roomAmenityID = roomAmenityID;
        }

        public RoomAmenitiesCrossRef(HotelRoom hotelRoom, RoomAmenity roomAmenity){
                this.roomID = hotelRoom.roomID;
                this.roomAmenityID = roomAmenity.getRoomAmenityID();
        }
}
