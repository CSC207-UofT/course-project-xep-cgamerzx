package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "uniqueId"})
public class RoomAmenitiesCrossRef {
        public long roomID;
        @NonNull
        @ColumnInfo(index = true)
        public String uniqueId;

        public RoomAmenitiesCrossRef(long roomID, @NonNull String uniqueId){
                this.roomID = roomID;
                this.uniqueId = uniqueId;
        }

        public RoomAmenitiesCrossRef(HotelRoom hotelRoom, RoomAmenity roomAmenity){
                this.roomID = hotelRoom.roomID;
                this.uniqueId = roomAmenity.getUniqueId();
        }
}