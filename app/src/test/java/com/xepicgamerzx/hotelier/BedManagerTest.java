package com.xepicgamerzx.hotelier;

import org.junit.Test;
import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.Room;
import com.xepicgamerzx.hotelier.storage.BedManager;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class BedManagerTest {

    @Test
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

        // Room 1:
        Bed bed1 = new Bed("King");

        // Room 2:
        Room room2 = new Room(
                1634281932,
                1636960332,
                4, room1_beds, 350);


        for (Bed bed : room1_beds) {
            assertNull(bed.getRoom());
        }

        bedManager.setRoomForAllBeds(room2, room1_beds);
        // Testing to see if all beds have a reference to a hotel.
        for(Bed bed : room1_beds) {
            assertNotNull(bed.getRoom());
        }

    }
}
