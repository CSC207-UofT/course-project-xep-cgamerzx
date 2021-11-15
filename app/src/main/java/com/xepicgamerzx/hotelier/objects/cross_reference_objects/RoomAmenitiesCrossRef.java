package com.xepicgamerzx.hotelier.objects.cross_reference_objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.xepicgamerzx.hotelier.objects.hotel_objects.RoomAmenity;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;

@Entity(primaryKeys = {"roomID", "uniqueId"}, inheritSuperIndices = true)
public class RoomAmenitiesCrossRef extends CrossRef {
        public long roomID;

        public RoomAmenitiesCrossRef(long roomID, @NonNull String uniqueId){
                this.roomID = roomID;
                this.uniqueId = uniqueId;
        }

        public RoomAmenitiesCrossRef(HotelRoom hotelRoom, RoomAmenity roomAmenity){
                this(hotelRoom.roomID, roomAmenity.getUniqueId());
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof RoomAmenitiesCrossRef)) return false;
                if (!super.equals(o)) return false;

                RoomAmenitiesCrossRef that = (RoomAmenitiesCrossRef) o;

                return roomID == that.roomID;
        }

        @Override
        public int hashCode() {
                int result = super.hashCode();
                result = 31 * result + (int) (roomID ^ (roomID >>> 32));
                return result;
        }
}