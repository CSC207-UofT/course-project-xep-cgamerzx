package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.xepicgamerzx.hotelier.customer.CustomerFilterManager;
import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;

import org.junit.Test;

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

        HotelRoom hotelRoom = new HotelRoom(
                1634281932,
                1636960332,
                4, beds, 250);
        bed1.setRoom(hotelRoom);

        List<HotelRoom> hotelRooms = new ArrayList<HotelRoom>();
        hotelRooms.add(hotelRoom);

        Hotel h = new Hotel("Hilton", address, hotelRooms);
        hotelRoom.setHotel(h);

        assertEquals(h.getName(), "Hilton");
        assertEquals(h.getAddress(), address);
        assertEquals(h.getRooms(), hotelRooms);
    }

    @Test
    public void testGetHotelPriceRange() {
        // Creating a hotel is kind of a long process
        // HotelRoom 1:
        Bed bed1 = new Bed("Queen");
        ArrayList<Bed> beds = new ArrayList<Bed>();
        beds.add(bed1);
        HotelRoom hotelRoom1 = new HotelRoom(
                1634281932,
                1636960332,
                4, beds, 250);
        bed1.setRoom(hotelRoom1);

        // HotelRoom 2:
        ArrayList<Bed> beds2 = new ArrayList<Bed>();
        Bed bed2 = new Bed("King");
        beds.add(bed2);
        HotelRoom hotelRoom2 = new HotelRoom(
                1634281932,
                1636960332,
                4, beds, 350);
        bed2.setRoom(hotelRoom2);

        // Creating the hotel
        List<HotelRoom> hotelRooms = new ArrayList<HotelRoom>();
        hotelRooms.add(hotelRoom1);
        hotelRooms.add(hotelRoom2);

        Address address = new Address("Testing Lane",
                "M5T2Y7",
                "123",
                "Toronto",
                "ON",
                43.6532,
                79.3832);

        Hotel h = new Hotel("Hilton", address, hotelRooms);
        hotelRoom1.setHotel(h);


        // Actual Test
        double[] actual = h.getPrinceRange();
        double[] expected = {250.0, 350};
        assertTrue(Arrays.equals(actual, expected));

    }

    @Test
    public void testSortHotelsByMinimumPrice() {
        // Creating the hotel 1
        // HotelRoom 1:
        Bed bed1 = new Bed("Queen");
        ArrayList<Bed> beds = new ArrayList<Bed>();
        beds.add(bed1);
        HotelRoom hotelRoom1 = new HotelRoom(
                1634281932,
                1636960332,
                4, beds, 250);
        bed1.setRoom(hotelRoom1);
        // HotelRoom 2:
        Bed bed2 = new Bed("King");
        beds.add(bed2);
        ArrayList<Bed> beds2 = new ArrayList<Bed>();
        beds.add(bed2);
        HotelRoom hotelRoom2 = new HotelRoom(
                1634281932,
                1636960332,
                4, beds2, 350);
        bed2.setRoom(hotelRoom2);
        List<HotelRoom> hotelRooms = new ArrayList<HotelRoom>();
        hotelRooms.add(hotelRoom1);
        hotelRooms.add(hotelRoom2);
        Address address = new Address("Testing Lane",
                "M5T2Y7",
                "123",
                "Toronto",
                "ON",
                43.6532,
                79.3832);
        Hotel h1 = new Hotel("Hilton", address, hotelRooms);
        hotelRoom1.setHotel(h1);

        //Hotel 2
        Bed bed4 = new Bed("Queen");
        HotelRoom hotelRoom4 = new HotelRoom(
                1634281932,
                1636960332,
                2, beds, 50);
        List<HotelRoom> rooms2 = new ArrayList<HotelRoom>();
        rooms2.add(hotelRoom4);
        Hotel h2 = new Hotel("Cool", address, rooms2);

        // Hotel 3
        HotelRoom hotelRoom5 = new HotelRoom(
                1634281932,
                1636960332,
                2, beds, 60);
        List<HotelRoom> rooms3 = new ArrayList<HotelRoom>();
        rooms3.add(hotelRoom5);
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
