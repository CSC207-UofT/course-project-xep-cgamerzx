package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.BedManager;

import org.junit.Test;

import java.util.ArrayList;

@Deprecated
public class BedManagerTest {

/*    @Test
    public void testCreateBeds() {
        ArrayList<Bed> beds = new ArrayList<Bed>();
        BedManager bedManager = new BedManager();

        beds.addAll(bedManager.createBeds(3, "King"));
        assertEquals(beds.size(), 3);
    }

    @Test
    public void testReferenceAllBedsToARoom() {
        ArrayList<Bed> room1_beds = new ArrayList<Bed>();
        BedManager bedManager = new BedManager();

        // HotelRoom 1:
        Bed bed1 = new Bed("King");

        // HotelRoom 2:
        HotelRoom hotelRoom2 = new HotelRoom(
                1634281932,
                1636960332,
                4, room1_beds, 350);


        for (Bed bed : room1_beds) {
            assertNull(bed.getRoom());
        }

        bedManager.setRoomForAllBeds(hotelRoom2, room1_beds);
        // Testing to see if all beds have a reference to a hotel.
        for(Bed bed : room1_beds) {
            assertNotNull(bed.getRoom());
        }

    }*/
}
