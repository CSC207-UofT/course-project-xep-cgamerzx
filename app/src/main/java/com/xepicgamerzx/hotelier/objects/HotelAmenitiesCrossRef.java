package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"hotelID", "uniqueId"})
public class HotelAmenitiesCrossRef extends CrossRef{
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