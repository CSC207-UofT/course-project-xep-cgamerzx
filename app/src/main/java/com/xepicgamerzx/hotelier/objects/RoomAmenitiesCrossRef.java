package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "id"})
public class RoomAmenitiesCrossRef {
        public long roomID;
        @NonNull
        @ColumnInfo(index = true)
        public String id;

        public RoomAmenitiesCrossRef(long roomID, @NonNull String id){
                this.roomID = roomID;
                this.id = id;
        }

        public RoomAmenitiesCrossRef(HotelRoom hotelRoom, RoomAmenity roomAmenity){
                this.roomID = hotelRoom.roomID;
                this.id = roomAmenity.getId();
        }
}
