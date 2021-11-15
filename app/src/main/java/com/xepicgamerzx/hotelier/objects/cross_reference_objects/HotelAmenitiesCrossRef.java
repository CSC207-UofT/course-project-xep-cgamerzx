package com.xepicgamerzx.hotelier.objects.cross_reference_objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;

@Entity(primaryKeys = {"hotelID", "uniqueId"}, inheritSuperIndices = true)
public class HotelAmenitiesCrossRef extends CrossRef {
        public long hotelID;

        public HotelAmenitiesCrossRef(long hotelID, @NonNull String uniqueId){
            this.hotelID = hotelID;
            this.uniqueId = uniqueId;
        }

        public HotelAmenitiesCrossRef(Hotel hotel, HotelAmenity hotelAmenity){
            this(hotel.hotelID, hotelAmenity.getUniqueId());
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelAmenitiesCrossRef)) return false;
        if (!super.equals(o)) return false;

        HotelAmenitiesCrossRef that = (HotelAmenitiesCrossRef) o;

        return hotelID == that.hotelID;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (hotelID ^ (hotelID >>> 32));
        return result;
    }
}