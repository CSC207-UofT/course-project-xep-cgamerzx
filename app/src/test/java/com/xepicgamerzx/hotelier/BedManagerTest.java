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
        System.out.println();
//        System.out.println(beds);
    }

    @Test
    public void testReferenceAllBedsToARoom() {

        ArrayList<Bed> room2_beds = new ArrayList<Bed>();
        BedManager bedManager = new BedManager();

        // Room 1: (could have used createBeds)...
        Bed bed1 = new Bed("Queen");
        Bed bed2 = new Bed("King");
        room2_beds.add(bed2);
        room2_beds.add(bed1);

        // Changed schedule field to epoch so its a long now, change later

        Room room2 = new Room(
                1634281932,
                1636960332,
                4, room2_beds, 350);


        // Test
        for (Bed bed : room2_beds) {
            assertNull(bed.getRoom());
        }

        bedManager.setRoomForAllBeds(room2, room2_beds);

        for(Bed bed : room2_beds) {
            assertNotNull(bed.getRoom());
        }

    }
}
