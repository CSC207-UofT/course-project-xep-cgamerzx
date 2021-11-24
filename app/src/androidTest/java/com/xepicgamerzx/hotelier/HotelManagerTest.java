package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelManager;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.hotel_managers.RoomManager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class HotelManagerTest {
    private static ArrayList<Address> addresses;

    private HotelierDatabase db;
    private HotelManager hotelManager;
    private RoomManager roomManager;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, HotelierDatabase.class).build();

        hotelManager = HotelManager.getManager(db);
        roomManager = RoomManager.getManager(db);
    }

    @BeforeClass
    public static void createBoilerInfo(){
        addresses = new ArrayList<>();

        Address address_1 = new Address("Testing Lane",
                "M5T2Y7",
                "123",
                "Toronto",
                "ON",
                43.6532,
                -79.3832);

        addresses.add(address_1);
    }


    @Test
    public void testNewStandaloneHotel() {
        String name = "Hilton";
        int starClass = 5;

        hotelManager.createHotel(name, addresses.get(0), starClass);

        List<Hotel> hotels = hotelManager.getAll();
        Hotel hotel = hotels.get(0);
        Address hotel_address = hotel.getAddress();

        assertEquals(hotel.getName(), name);
        assertEquals(hotel.getStarClass(), starClass);
        assert (addresses.get(0).equals(hotel_address));
    }

    @Test
    public void testRoomHotel() {
        ZoneId zoneId = ZoneId.systemDefault();
        BigDecimal price = BigDecimal.valueOf(200.91);
        long startDate = System.currentTimeMillis();
        long endDate = startDate * 2;
        int capacity = 5;

        HotelRoom room1 = roomManager.createRoom(zoneId, startDate, endDate, capacity, price);
        HotelRoom room2 = roomManager.createRoom(zoneId, startDate, endDate, capacity, price.multiply(BigDecimal.valueOf(2)));
        HotelRoom[] rooms = {room1, room2};

        String name = "Hotel With Rooms Test";
        int starClass = 5;
        Hotel hotel = hotelManager.createHotel(name, addresses.get(0), starClass, Arrays.asList(rooms));
        assertEquals(room1.getHotelID(), room2.getHotelID(), hotel.hotelID);
    }

    @Test
    public void testGetHotelsInArea(){
        double montrealLat = 45.5017;
        double montrealLon = -73.561668;
        double approxDistanceBetweenMT =  550;

        String name = "Hilton";
        int starClass = 5;

        Hotel testHotel1 = hotelManager.createHotel(name, addresses.get(0), starClass);

        assert (!hotelManager.getHotelsInArea(montrealLat, montrealLon, 5).contains(testHotel1));
        assert (hotelManager.getHotelsInArea(montrealLat, montrealLon, approxDistanceBetweenMT).contains(testHotel1));
    }


    @After
    public void closeDb() {
        roomManager.close();
        hotelManager.close();
        db.close();
    }
}

