package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import android.app.Application;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class HotelMangerTest {

    private HotelManager hotelManager;

    @Before
    public void createDb() {
        Application application = ApplicationProvider.getApplicationContext();
        hotelManager = new HotelManager(application);
    }

    @After
    public void closeDb(){
        hotelManager.closeDB();
    }

    @Test
    public void testNewStandaloneHotel() {
        Address address = new Address("Testing Lane",
                "M5T2Y7",
                "123",
                "Toronto",
                "ON",
                43.6532,
                79.3832);

        String name = "Hilton";
        int starClass = 5;

        hotelManager.createHotel(name, address, starClass);

        List<Hotel> hotels = hotelManager.getAllHotels();
        Hotel hotel = hotels.get(0);
        Address hotel_address = hotel.getAddress();

        assertEquals(hotel.getName(), "Hilton");
        assertEquals(hotel.getStarClass(), starClass);
        assert (address.equals(hotel_address));
    }
}

