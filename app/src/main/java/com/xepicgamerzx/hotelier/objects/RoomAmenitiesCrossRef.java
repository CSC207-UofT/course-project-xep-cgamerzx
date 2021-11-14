package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "uniqueId"})
public class RoomAmenitiesCrossRef extends CrossRef{
        public long roomID;

        public RoomAmenitiesCrossRef(long roomID, @NonNull String uniqueId){
                this.roomID = roomID;
                this.uniqueId = uniqueId;
        }

        public RoomAmenitiesCrossRef(HotelRoom hotelRoom, RoomAmenity roomAmenity){
                this.roomID = hotelRoom.roomID;
                this.uniqueId = roomAmenity.getUniqueId();
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