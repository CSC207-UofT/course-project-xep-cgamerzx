package com.xepicgamerzx.hotelier;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.Room;
import java.util.ArrayList;

public class RoomTest {

    @Test
    public void testNewRoomObject() {  // tests each getter method for the Room object

        // this chunk creates an array list and adds 2 beds to it
        Bed bed1 = new Bed("Queen");
        Bed bed2 = new Bed("Queen");
        ArrayList<Bed> beds = new ArrayList<>();
        beds.add(bed1);
        beds.add(bed2);

        // this chunk creates a room with the specified parameters
        Room room = new Room(1636084800, 1636257600,
                4, beds, 500);

        // this chunk creates a long object specifying the room's availability
        long[] schedule = new long[2];
        schedule[0] = 1636084800;
        schedule[1] = 1636257600;

        // tests the getter methods for beds, schedule, capacity and price
        assertEquals(room.getBeds(), beds);
        assertEquals(room.getSchedule()[0], schedule[0]);
        assertEquals(room.getSchedule()[1], schedule[1]);
        assertEquals(room.getCapacity(), 4);
        assertEquals(room.getPrice(), 500, 0);
    }


    @Test
    public void testRoomWithNoHotelReference() {

        // this chunk creates an array list and adds 1 bed to it
        Bed bed = new Bed("Queen");
        ArrayList<Bed> beds = new ArrayList<>();
        beds.add(bed);

        // this chunk creates a room with the specified parameters
        Room room = new Room(1636084800, 1636257600,
                2, beds, 250);

        assertNull(room.getHotel()); // tests that the room is not assigned to a hotel
    }
}