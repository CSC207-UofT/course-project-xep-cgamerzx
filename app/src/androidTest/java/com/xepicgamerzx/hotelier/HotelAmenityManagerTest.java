package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.HotelAmenitiesEnum;
import com.xepicgamerzx.hotelier.storage.HotelAmenityManager;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.RoomManager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class HotelAmenityManagerTest {

    private static ArrayList<Address> addresses;
    private HotelierDatabase db;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private HotelAmenityManager hotelAmenityManager;
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
        hotelAmenityManager = HotelAmenityManager.getManager(db);

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

        HotelAmenity amenity1 = hotelAmenityManager.get("Laundry").get(0);
        HotelAmenity amenity2 = hotelAmenityManager.get(HotelAmenitiesEnum.GYM.toString()).get(0);

        assertEquals(amenity1.getUniqueId(), HotelAmenitiesEnum.LAUNDRY.label);
        assertEquals(amenity2.getUniqueId(), HotelAmenitiesEnum.GYM.label);
    }


    @Test
    public void testAddAmenityToHotel() {
        HotelAmenity amenity1 = hotelAmenityManager.create("Laundry");
        HotelAmenity amenity2 = hotelAmenityManager.create(HotelAmenitiesEnum.GYM);

        hotelManager.addAmenityToHotel(testHotel, amenity1);
        hotelManager.addAmenityToHotel(testHotel, amenity2);

        // TODO : find out how to get a hotel's amenities? to finish this test
    }


    @After
    public void closeDb() {
        roomManager.close();
        hotelAmenityManager.close();
        hotelManager.close();
        db.close();
    }

}
