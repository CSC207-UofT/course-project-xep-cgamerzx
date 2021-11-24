package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.AddressBuilder;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomAmenitiesEnum;
import com.xepicgamerzx.hotelier.objects.hotel_objects.RoomAmenity;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelManager;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.hotel_reference_managers.RoomAmenitiesCrossManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.RoomAmenityManager;
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
public class RoomAmenityManagerTest {

    private static ArrayList<Address> addresses;
    private HotelierDatabase db;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private RoomAmenityManager roomAmenityManager;
    private RoomAmenitiesCrossManager roomAmenitiesCrossManager;
    private final ZoneId zoneId = ZoneId.systemDefault();
    private final BigDecimal price = BigDecimal.valueOf(200.91);
    private final long startDate = System.currentTimeMillis();
    private final long endDate = startDate * 2;

    @BeforeClass
    public static void createBoilerInfo() {
        addresses = new ArrayList<>();

        Address address_1 = new AddressBuilder()
                .setStreetName("Testing Lane")
                .setPostalCode("M5T2Y7")
                .setStreetNumber("123")
                .setCity("Toronto")
                .setProvince("ON")
                .setLatitude(43.6532)
                .setLongitude(-79.3832)
                .build();

        addresses.add(address_1);
    }

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, HotelierDatabase.class).build();

        hotelManager = HotelManager.getManager(db);
        roomManager = RoomManager.getManager(db);
        roomAmenityManager = RoomAmenityManager.getManager(db);
        roomAmenitiesCrossManager = RoomAmenitiesCrossManager.getManager(db);

        ArrayList<HotelRoom> rooms = new ArrayList<>();

        int numHotels = 10;
        for (int i = 1; i <= numHotels; i++) {
            int capacity = 5;
            rooms.add(roomManager.createRoom(zoneId, startDate, endDate, capacity, price.multiply(BigDecimal.valueOf(i))));
        }

        String name = "Gamer Hotel";
        int starClass = 5;
        hotelManager.createHotel(name, addresses.get(0), starClass, rooms);
    }

    @Test
    public void testCreate() {
        roomAmenityManager.create("Patio");
        roomAmenityManager.create(RoomAmenitiesEnum.WIFI);

        RoomAmenity amenity1 = roomAmenityManager.get("Patio").get(0);
        RoomAmenity amenity2 = roomAmenityManager.get(RoomAmenitiesEnum.WIFI.toString()).get(0);

        assertEquals(amenity1.getUniqueId(), RoomAmenitiesEnum.PATIO.getLabel());
        assertEquals(amenity2.getUniqueId(), RoomAmenitiesEnum.WIFI.getLabel());
    }

    @Test
    public void testAddAmenityToRoom() {
        RoomAmenity amenity1 = roomAmenityManager.create("Patio");
        RoomAmenity amenity2 = roomAmenityManager.create(RoomAmenitiesEnum.WIFI);

        HotelRoom room = roomManager.getAll().get(1);

        roomAmenitiesCrossManager.createRelationship(room, amenity1);
        roomAmenitiesCrossManager.createRelationship(room, amenity2);

        List <RoomAmenity> actual = roomAmenitiesCrossManager.getRelated(room);
        List <RoomAmenity> expected = Arrays.asList(amenity1, amenity2);

        assertEquals(actual, expected);
    }

    @After
    public void closeDb() {
        roomManager.close();
        roomAmenityManager.close();
        hotelManager.close();
        roomAmenitiesCrossManager.close();
        db.close();
    }

}
