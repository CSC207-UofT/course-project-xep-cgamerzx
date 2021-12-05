package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.AddressBuilder;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.hotel_objects.BedSizeEnum;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.hotel_managers.BedManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.RoomManager;
import com.xepicgamerzx.hotelier.storage.hotel_reference_managers.RoomBedsCrossManager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class RoomBedsCrossManagerTest {

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
    private RoomBedsCrossManager roomBedsCrossManager;

    @BeforeClass
    public static void createBoilerInfo() {
        addresses = new ArrayList<>();

        Address address_1 = new AddressBuilder().setStreetName("Testing Lane").setPostalCode("M5T2Y7").setStreetNumber("123").setCity("Toronto").setProvince("ON").setLatitude(43.6532).setLongitude(-79.3832).build();

        addresses.add(address_1);
    }

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, HotelierDatabase.class).build();

        hotelManager = HotelManager.getManager(db);
        roomManager = RoomManager.getManager(db);
        bedManager = BedManager.getManager(db);
        roomBedsCrossManager = RoomBedsCrossManager.getManager(db);

        ArrayList<HotelRoom> rooms = new ArrayList<>();

        for (int i = 1; i <= numHotels; i++) {
            rooms.add(roomManager.createRoom(zoneId, startDate, endDate, capacity, price.multiply(BigDecimal.valueOf(i))));
        }

        String name = "RoomManager Test Hotel 1";
        int starClass = 5;
    }

    @Test
    public void testCreate() {
        bedManager.create(BedSizeEnum.KING);
        bedManager.create("Queen");

        Bed bed = db.bedDao().getIdMatch(BedSizeEnum.KING.toString()).get(0);

        assertEquals(bed.getUniqueId(), BedSizeEnum.KING.getLabel());
    }

    @Test
    public void testAddBedToRoom() {
        Bed bedK = bedManager.create(BedSizeEnum.KING);
        Bed bedT = bedManager.create("Test Bed Type");

        List<HotelRoom> rooms = db.roomDao().getAll();
        roomBedsCrossManager.createRelationship(rooms.get(1), bedK, 3);
        roomBedsCrossManager.createRelationship(rooms.get(1), bedT, 2);
        roomBedsCrossManager.createRelationship(rooms.get(0), bedT, 1);

        assert (roomBedsCrossManager.getRelated(bedT).contains(rooms.get(1)));
        assert (roomBedsCrossManager.getRelated(bedT).contains(rooms.get(0)));
        assert (roomBedsCrossManager.getRelated(bedK).contains(rooms.get(1)));

        assert (roomBedsCrossManager.getRelated(rooms.get(0)).contains(bedT));
        assert (roomBedsCrossManager.getRelated(rooms.get(1)).contains(bedT));
        assert (roomBedsCrossManager.getRelated(rooms.get(1)).contains(bedK));
    }

    @Test
    public void testGetBedsInRoomCount() {
        Bed bedK = bedManager.create(BedSizeEnum.KING);
        Bed bedT = bedManager.create("Test Bed Type");

        int k1Count = 3;
        int t1Count = 2;
        int t0Count = 1;

        List<HotelRoom> rooms = db.roomDao().getAll();
        roomBedsCrossManager.createRelationship(rooms.get(1), bedK, k1Count);
        roomBedsCrossManager.createRelationship(rooms.get(1), bedT, t1Count);
        roomBedsCrossManager.createRelationship(rooms.get(0), bedT, t0Count);

        assert (roomBedsCrossManager.getBedsInRoomCount(rooms.get(0)).get(bedT) == t0Count);
        assert (roomBedsCrossManager.getBedsInRoomCount(rooms.get(1)).get(bedK) == k1Count);
        assert (roomBedsCrossManager.getBedsInRoomCount(rooms.get(1)).get(bedT) == t1Count);
    }

    @Test
    public void testGetRoomsWithBed() {
        Bed bedK = bedManager.create(BedSizeEnum.KING);
        Bed bedT = bedManager.create("Test Bed Type");

        int k1Count = 3;
        int t1Count = 2;
        int t0Count = 1;

        List<HotelRoom> rooms = db.roomDao().getAll();
        roomBedsCrossManager.createRelationship(rooms.get(1), bedK, k1Count);
        roomBedsCrossManager.createRelationship(rooms.get(1), bedT, t1Count);
        roomBedsCrossManager.createRelationship(rooms.get(0), bedT, t0Count);

        assert (roomBedsCrossManager.getRelated(bedK, k1Count).contains(rooms.get(1)));
        assert (!roomBedsCrossManager.getRelated(bedK, k1Count).contains(rooms.get(0)));

        assert (roomBedsCrossManager.getRelated(bedT, t1Count).contains(rooms.get(1)));
        assert (!roomBedsCrossManager.getRelated(bedT, t1Count).contains(rooms.get(0)));

        assert (roomBedsCrossManager.getRelated(bedT, t0Count).contains(rooms.get(1)));
        assert (roomBedsCrossManager.getRelated(bedT, t0Count).contains(rooms.get(0)));
    }

    @After
    public void closeDb() {
        roomManager.close();
        bedManager.close();
        hotelManager.close();
        roomBedsCrossManager.close();
        db.close();
    }
}

