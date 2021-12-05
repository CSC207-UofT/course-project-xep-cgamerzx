package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.AddressBuilder;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenitiesEnum;
import com.xepicgamerzx.hotelier.storage.hotel_reference_managers.HotelAmenitiesCrossManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelAmenityManager;
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
public class HotelAmenityManagerTest {

    private static ArrayList<Address> addresses;
    private HotelierDatabase db;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private HotelAmenityManager hotelAmenityManager;
    private HotelAmenitiesCrossManager hotelAmenitiesCrossManager;
    private final ZoneId zoneId = ZoneId.systemDefault();
    private final BigDecimal price = BigDecimal.valueOf(200.91);
    private final long startDate = System.currentTimeMillis();
    private final long endDate = startDate * 2;
    private Hotel testHotel;

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
        hotelAmenityManager = HotelAmenityManager.getManager(db);
        hotelAmenitiesCrossManager = HotelAmenitiesCrossManager.getManager(db);

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
        hotelAmenityManager.create("Laundry");
        hotelAmenityManager.create(HotelAmenitiesEnum.GYM);

        HotelAmenity amenity1 = db.hotelAmenityDao().getIdMatch("Laundry").get(0);
        HotelAmenity amenity2 = db.hotelAmenityDao().getIdMatch(HotelAmenitiesEnum.GYM.toString()).get(0);

        assertEquals(amenity1.getUniqueId(), HotelAmenitiesEnum.LAUNDRY.getLabel());
        assertEquals(amenity2.getUniqueId(), HotelAmenitiesEnum.GYM.getLabel());
    }


    @Test
    public void testAddAmenityToHotel() {
        HotelAmenity amenity1 = hotelAmenityManager.create("Laundry");
        HotelAmenity amenity2 = hotelAmenityManager.create(HotelAmenitiesEnum.GYM);

        hotelAmenitiesCrossManager.createRelationship(testHotel, amenity1);
        hotelAmenitiesCrossManager.createRelationship(testHotel, amenity2);

        List<HotelAmenity> actual = hotelAmenitiesCrossManager.getRelated(testHotel);
        List<HotelAmenity> expected = Arrays.asList(amenity2, amenity1);

        assertEquals(actual, expected);
    }


    @After
    public void closeDb() {
        roomManager.close();
        hotelAmenityManager.close();
        hotelManager.close();
        hotelAmenitiesCrossManager.close();
        db.close();
    }

}
