package com.xepicgamerzx.hotelier.objects.cross_reference_objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;

@Entity(primaryKeys = {"hotelId", "uniqueId"}, inheritSuperIndices = true)
public class HotelAmenitiesCrossRef extends CrossRef {
    public long hotelId;

    public HotelAmenitiesCrossRef(long hotelId, @NonNull String uniqueId) {
        this.hotelId = hotelId;
        this.uniqueId = uniqueId;
    }

    public HotelAmenitiesCrossRef(Hotel hotel, HotelAmenity hotelAmenity) {
        this(hotel.hotelId, hotelAmenity.getUniqueId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelAmenitiesCrossRef)) return false;
        if (!super.equals(o)) return false;

        HotelAmenitiesCrossRef that = (HotelAmenitiesCrossRef) o;

        return hotelId == that.hotelId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (hotelId ^ (hotelId >>> 32));
        return result;
    }
}