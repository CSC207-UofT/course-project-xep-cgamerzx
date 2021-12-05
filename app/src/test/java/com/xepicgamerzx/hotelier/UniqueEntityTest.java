package com.xepicgamerzx.hotelier;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.hotel_objects.RoomAmenity;

import org.junit.Test;

public class UniqueEntityTest {
    @Test
    public void TestSubclassInequality() {
        Bed bed = new Bed("entity");
        HotelAmenity hotelAmenity = new HotelAmenity("entity");
        RoomAmenity roomAmenity1 = new RoomAmenity("entity");
        RoomAmenity roomAmenity2 = new RoomAmenity("entity");

        assert(!bed.equals(hotelAmenity));
        assert(!bed.equals(roomAmenity1));
        assert(!roomAmenity1.equals(hotelAmenity));

        assert(roomAmenity1.equals(roomAmenity2));
    }
}
