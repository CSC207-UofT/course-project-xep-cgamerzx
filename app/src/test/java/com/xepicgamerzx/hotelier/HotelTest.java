package com.xepicgamerzx.hotelier;

import org.junit.Test;

import static org.junit.Assert.*;

import com.xepicgamerzx.hotelier.customer.CustomerFilterManager;
import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.Room;
import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.Hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelTest {

    @Test
    public void testNewHotelObject() {
        Address address = new Address("Testing Lane",
                "M5T2Y7",
                "123",
                "Toronto",
                "ON",
                43.6532,
                79.3832);
        Bed bed1 = new Bed("Queen");

        ArrayList<Bed> beds = new ArrayList<Bed>();
        beds.add(bed1);

        Room room = new Room(
                1634281932,
                1636960332,
                4, beds, 250);
        bed1.setRoom(room);

        List<Room> rooms = new ArrayList<Room>();
        rooms.add(room);

        Hotel h = new Hotel("Hilton", address, rooms);
        room.setHotel(h);

        assertEquals(h.getName(), "Hilton");
        assertEquals(h.getAddress(), address);
        assertEquals(h.getRooms(), rooms);
    }

    @Test
    public void testGetHotelPriceRange() {
        // Creating a hotel is kind of a long process
        // Room 1:
        Bed bed1 = new Bed("Queen");
        ArrayList<Bed> beds = new ArrayList<Bed>();
        beds.add(bed1);
        Room room1 = new Room(
                1634281932,
                1636960332,
                4, beds, 250);
        bed1.setRoom(room1);

        // Room 2:
        ArrayList<Bed> beds2 = new ArrayList<Bed>();
        Bed bed2 = new Bed("King");
        beds.add(bed2);
        Room room2 = new Room(
                1634281932,
                1636960332,
                4, beds, 350);
        bed2.setRoom(room2);

        // Creating the hotel
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(room1);
        rooms.add(room2);

        Address address = new Address("Testing Lane",
                "M5T2Y7",
                "123",
                "Toronto",
                "ON",
                43.6532,
                79.3832);

        Hotel h = new Hotel("Hilton", address, rooms);
        room1.setHotel(h);


        // Actual Test
        double[] actual = h.getPrinceRange();
        double[] expected = {250.0, 350};
        assertTrue(Arrays.equals(actual, expected));

    }

    @Test
    public void testSortHotelsByMinimumPrice() {
        // Creating the hotel 1
        // Room 1:
        Bed bed1 = new Bed("Queen");
        ArrayList<Bed> beds = new ArrayList<Bed>();
        beds.add(bed1);
        Room room1 = new Room(
                1634281932,
                1636960332,
                4, beds, 250);
        bed1.setRoom(room1);
        // Room 2:
        Bed bed2 = new Bed("King");
        beds.add(bed2);
        ArrayList<Bed> beds2 = new ArrayList<Bed>();
        beds.add(bed2);
        Room room2 = new Room(
                1634281932,
                1636960332,
                4, beds2, 350);
        bed2.setRoom(room2);
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(room1);
        rooms.add(room2);
        Address address = new Address("Testing Lane",
                "M5T2Y7",
                "123",
                "Toronto",
                "ON",
                43.6532,
                79.3832);
        Hotel h1 = new Hotel("Hilton", address, rooms);
        room1.setHotel(h1);

        //Hotel 2
        Bed bed4 = new Bed("Queen");
        Room room4 = new Room(
                1634281932,
                1636960332,
                2, beds, 50);
        List<Room> rooms2 = new ArrayList<Room>();
        rooms2.add(room4);
        Hotel h2 = new Hotel("Cool", address, rooms2);

        // Hotel 3
        Room room5 = new Room(
                1634281932,
                1636960332,
                2, beds, 60);
        List<Room> rooms3= new ArrayList<Room>();
        rooms3.add(room5);
        Hotel h3 = new Hotel("Bruh", address, rooms3);


        // Test
        List<Hotel> hotels = new ArrayList<Hotel>();
        hotels.add(h1);
        hotels.add(h2);
        hotels.add(h3);


        CustomerFilterManager fm = new CustomerFilterManager();

        List<Hotel> actualSortedHotels = fm.sortHotelsByPrice(hotels);
        List<Hotel> expectedSortedHotels = new ArrayList<>();
        expectedSortedHotels.add(h2);
        expectedSortedHotels.add(h3);
        expectedSortedHotels.add(h1);

        assertEquals(actualSortedHotels, expectedSortedHotels);

    }
}
