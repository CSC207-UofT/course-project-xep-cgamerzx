package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.BedSizeEnum;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.BedManager;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.RoomManager;

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
public class RoomMangerTest {
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
        testHotel = hotelManager.createHotel(name, addresses.get(0), starClass, rooms.toArray(new HotelRoom[0]));
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

    @Test
    public void testGetRoomsWithBed(){
        Bed bedK = bedManager.create(BedSizeEnum.KING);
        Bed bedT = bedManager.create("Test Bed Type");

        int k1Count = 3;
        int t1Count = 2;
        int t0Count = 1;

        List<HotelRoom> rooms = roomManager.getAll();
        bedManager.addBedToRoom(bedK, rooms.get(1), k1Count);
        bedManager.addBedToRoom(bedT, rooms.get(1), t1Count);
        bedManager.addBedToRoom(bedT, rooms.get(0), t0Count);

        assert(roomManager.getRoomsWithBed(bedK, k1Count).contains(rooms.get(1)));
        assert(!roomManager.getRoomsWithBed(bedK, k1Count).contains(rooms.get(0)));

        assert(roomManager.getRoomsWithBed(bedT, t1Count).contains(rooms.get(1)));
        assert(!roomManager.getRoomsWithBed(bedT, t1Count).contains(rooms.get(0)));

        assert(roomManager.getRoomsWithBed(bedT, t0Count).contains(rooms.get(1)));
        assert(roomManager.getRoomsWithBed(bedT, t0Count).contains(rooms.get(0)));
    }

    @After
    public void closeDb() {
        roomManager.close();
        hotelManager.close();
        bedManager.close();
        db.close();
    }
}

