package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.RoomAmenitiesEnum;
import com.xepicgamerzx.hotelier.objects.RoomAmenity;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.RoomAmenityManager;
import com.xepicgamerzx.hotelier.storage.RoomManager;

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
public class RoomAmenityManagerTest {

    private static ArrayList<Address> addresses;
    private HotelierDatabase db;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private RoomAmenityManager roomAmenityManager;
    private final ZoneId zoneId = ZoneId.systemDefault();
    private final BigDecimal price = BigDecimal.valueOf(200.91);
    private final long startDate = System.currentTimeMillis();
    private final long endDate = startDate * 2;
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
        roomAmenityManager = RoomAmenityManager.getManager(db);

        ArrayList<HotelRoom> rooms = new ArrayList<>();

        int numHotels = 10;
        for (int i = 1; i <= numHotels; i++) {
            int capacity = 5;
            rooms.add(roomManager.createRoom(zoneId, startDate, endDate, capacity, price.multiply(BigDecimal.valueOf(i))));
        }

        String name = "Gamer Hotel";
        int starClass = 5;
        testHotel = hotelManager.createHotel(name, addresses.get(0), starClass, rooms);
    }

    @Test
    public void testCreate() {
        roomAmenityManager.create("Patio");
        roomAmenityManager.create(RoomAmenitiesEnum.WIFI);

        RoomAmenity amenity1 = roomAmenityManager.get("Patio").get(0);
        RoomAmenity amenity2 = roomAmenityManager.get(RoomAmenitiesEnum.WIFI.toString()).get(0);

        assertEquals(amenity1.getUniqueId(), RoomAmenitiesEnum.PATIO.label);
        assertEquals(amenity2.getUniqueId(), RoomAmenitiesEnum.WIFI.label);
    }

    @Test
    public void testAddAmenityToRoom() {
        RoomAmenity amenity1 = roomAmenityManager.create("Patio");
        RoomAmenity amenity2 = roomAmenityManager.create(RoomAmenitiesEnum.WIFI);

        List<HotelRoom> rooms = roomManager.getAll();

        roomManager.addAmenityToRoom(rooms.get(0), amenity1);
        roomManager.addAmenityToRoom(rooms.get(0), amenity2);
        roomManager.addAmenityToRoom(rooms.get(1), amenity1);

        assert(roomAmenityManager.getAmenitiesInRoom(rooms.get(0)).contains(amenity1));
        assert(roomAmenityManager.getAmenitiesInRoom(rooms.get(0)).contains(amenity2));
        assert(roomAmenityManager.getAmenitiesInRoom(rooms.get(1)).contains(amenity1));
    }

    @After
    public void closeDb() {
        roomManager.close();
        roomAmenityManager.close();
        hotelManager.close();
        db.close();
    }

}
