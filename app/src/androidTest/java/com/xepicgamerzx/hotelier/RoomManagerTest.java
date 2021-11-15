package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.hotel_managers.BedManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelManager;
import com.xepicgamerzx.hotelier.storage.hotelier_database.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.hotel_managers.RoomManager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class RoomManagerTest {
    private static ArrayList<Address> addresses;
    private final ZoneId zoneId = ZoneId.systemDefault();
    private final BigDecimal price = BigDecimal.valueOf(200.91);
    private final long startDate = System.currentTimeMillis();
    private final long endDate = startDate * 2;
    private final int capacity = 5;
    private final int numHotels = 10;
    private HotelierDatabase db;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private BedManager bedManager;
    private Hotel testHotel;

    @BeforeClass
    public static void createBoilerInfo() {
        addresses = new ArrayList<>();

        Address address_1 = new Address("Testing Lane",
                "M5T2Y7",
                "123",
                "Toronto",
                "ON",
                43.6532,
                79.3832);

        addresses.add(address_1);
    }

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, HotelierDatabase.class).build();

        hotelManager = HotelManager.getManager(db);
        roomManager = RoomManager.getManager(db);
        bedManager = BedManager.getManager(db);

        ArrayList<HotelRoom> rooms = new ArrayList<>();

        for (int i = 1; i <= numHotels; i++) {
            rooms.add(roomManager.createRoom(zoneId, startDate, endDate, capacity, price.multiply(BigDecimal.valueOf(i))));
        }

        String name = "RoomManager Test Hotel 1";
        int starClass = 5;
        testHotel = hotelManager.createHotel(name, addresses.get(0), starClass, rooms);
    }

    @Test
    public void testRoomStandalone() {
        ZoneId zoneId = ZoneId.systemDefault();
        BigDecimal price = BigDecimal.valueOf(200.91);
        long startDate = System.currentTimeMillis();
        long endDate = startDate * 2;
        int capacity = 5;

        HotelRoom room = roomManager.createRoom(zoneId, startDate, endDate, capacity, price);

        HotelRoom dbRoom = roomManager.get(room.roomID).get(0);

        assertEquals(dbRoom, room);
    }

    @Test
    public void testGetNumberOfRooms() {
        int num = roomManager.getNumberOfRooms(testHotel);

        assertEquals(num, numHotels);
    }

    @Test
    public void testPriceRange() {
        BigDecimal max = price.multiply(BigDecimal.valueOf(numHotels)).setScale(2, RoundingMode.UP);

        List<BigDecimal> priceRange = roomManager.getPriceRange(testHotel);
        List<BigDecimal> expectedPriceRange = new ArrayList<>(List.of(price, max));

        assertEquals(priceRange, expectedPriceRange);
    }



    @After
    public void closeDb() {
        roomManager.close();
        hotelManager.close();
        bedManager.close();
        db.close();
    }
}

