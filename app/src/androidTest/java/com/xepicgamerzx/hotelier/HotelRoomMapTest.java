package com.xepicgamerzx.hotelier;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.AddressBuilder;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.hotel_managers.BedManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.RoomManager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RunWith(AndroidJUnit4.class)
public class HotelRoomMapTest {
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
    private Hotel testHotel1;
    private Hotel testHotel2;

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

        ArrayList<HotelRoom> rooms = new ArrayList<>();

        for (int i = 1; i <= numHotels; i++) {
            rooms.add(roomManager.createRoom(zoneId, startDate, endDate, capacity, price.multiply(BigDecimal.valueOf(i))));
        }
        String name = "RoomManager Test Hotel 1";
        int starClass = 5;
        testHotel1 = hotelManager.createHotel(name, addresses.get(0), starClass, rooms);
        testHotel2 = hotelManager.createHotel("RoomManager Test Hotel 2", addresses.get(0), 3);
    }

    @Test
    public void testGetAvailableRoomsWithHotel() {
        HotelRoom room1 = roomManager.createRoom(zoneId, 0, 5, capacity, price.multiply(BigDecimal.valueOf(100000)));
        HotelRoom room2 = roomManager.createRoom(zoneId, 0, 4, capacity, price.multiply(BigDecimal.valueOf(100000)));
        HotelRoom room3 = roomManager.createRoom(zoneId, 2, 3, capacity, price.multiply(BigDecimal.valueOf(100000)));

        roomManager.setHotelID(testHotel1, room1);
        roomManager.setHotelID(testHotel2, room2);
        roomManager.setHotelID(testHotel1, room3);

        Map<Hotel, List<HotelRoom>> map1 = db.hotelRoomMapDao().getAvailableRooms(2L, 3, 1, testHotel1.hotelId);
        Map<Hotel, List<HotelRoom>> map2 = db.hotelRoomMapDao().getAvailableRooms(2L, 3, 1, testHotel1.hotelId, testHotel2.hotelId);
        Map<Hotel, List<HotelRoom>> map3 = db.hotelRoomMapDao().getAvailableRooms(0L, 3, 1, testHotel1.hotelId, testHotel2.hotelId);


        assert (Objects.requireNonNull(map1.get(testHotel1)).size() == 2);
        assert (map2.keySet().size() == 2);
        assert (map3.keySet().size() == 2);
        assert (Objects.requireNonNull(map3.get(testHotel1)).contains(room1));
        assert (Objects.requireNonNull(map3.get(testHotel2)).contains(room2));
    }

    @After
    public void closeDb() {
        roomManager.close();
        hotelManager.close();
        bedManager.close();
        db.close();
    }
}
