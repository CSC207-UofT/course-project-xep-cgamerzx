package com.xepicgamerzx.hotelier.objects.cross_reference_objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.hotel_objects.RoomAmenity;

@Entity(primaryKeys = {"roomId", "uniqueId"}, inheritSuperIndices = true)
public class RoomAmenitiesCrossRef extends CrossRef {
    public long roomId;

    /**
     * Create a new RoomAmenitiesCrossRef.
     * @param roomId    the id of the room we are cross-referencing
     * @param uniqueId  the unique id of the amenity we are cross-referencing
     */
    public RoomAmenitiesCrossRef(long roomId, @NonNull String uniqueId) {
        this.roomId = roomId;
        this.uniqueId = uniqueId;
    }

    /**
     * Create a new RoomAmenitiesCrossRef
     * @param hotelRoom     the hotel room we are cross referencing
     * @param roomAmenity   the room amenity we are cross-referencing
     */
    public RoomAmenitiesCrossRef(HotelRoom hotelRoom, RoomAmenity roomAmenity) {
        this(hotelRoom.roomId, roomAmenity.getUniqueId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomAmenitiesCrossRef)) return false;
        if (!super.equals(o)) return false;

        RoomAmenitiesCrossRef that = (RoomAmenitiesCrossRef) o;

        return roomId == that.roomId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (roomId ^ (roomId >>> 32));
        return result;
    }
}