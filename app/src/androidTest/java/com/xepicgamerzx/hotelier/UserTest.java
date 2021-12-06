package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.AddressBuilder;
import com.xepicgamerzx.hotelier.storage.user.model.User;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class UserTest {

    @Test
    public void testNewUser() {
        User user = new User("epic_gamer", "password123",
                "epicgamer@gmail.com");

        assertEquals(user.getUserName(), "epic_gamer");
        assertEquals(user.getPassword(), "password123");
        assertEquals(user.getEmail(), "epicgamer@gmail.com");
    }

    @Test
    public void testUserSetters() {
        User user = new User("epic_gamer", "password1",
                "epicgamer@gmail.com");
        user.setId(1);
        user.setUserName("epic_gamer2");
        user.setPassword("password2");
        user.setEmail("epicgamer2@gmail.com");

        assertEquals(user.getId(), 1);
        assertEquals(user.getUserName(), "epic_gamer2");
        assertEquals(user.getPassword(), "password2");
        assertEquals(user.getEmail(), "epicgamer2@gmail.com");
    }

    @Test
    public void testUserWithNoIdReference() {
        User user = new User("epic_gamer", "password123",
                "epicgamer@gmail.com");

        assertEquals(user.getId(), 0);
    }

    @Test
    public void testAddFavHotel() {
        User user = new User("epic_gamer", "password123",
                "epicgamer@gmail.com");

        Address address_1 = new AddressBuilder().setStreetName("Testing Lane").setPostalCode("M5T2Y7").setStreetNumber("123").setCity("Toronto").setProvince("ON").setLatitude(43.6532).setLongitude(-79.3832).build();
        Address address_2 = new AddressBuilder().setStreetName("Testing Lane 2").setPostalCode("M5T2Y7").setStreetNumber("321").setCity("Toronto").setProvince("ON").setLatitude(33.6532).setLongitude(-60.3832).build();
        Hotel hotel1 = new Hotel("Gamer Hotel", address_1, 5);
        Hotel hotel2 = new Hotel("Epic Hotel", address_2, 4);

        user.addFavHotel(hotel1);
        user.addFavHotel(hotel2);

        ArrayList<Long> hotel_list = new ArrayList<>();
        hotel_list.add(hotel1.hotelId);
        hotel_list.add(hotel2.hotelId);

        assertEquals(user.getFavHotelIds(), hotel_list);
    }

    @Test
    public void testRemoveFavHotel() {
        User user = new User("epic_gamer", "password123",
                "epicgamer@gmail.com");

        Address address_1 = new AddressBuilder().setStreetName("Testing Lane").setPostalCode("M5T2Y7").setStreetNumber("123").setCity("Toronto").setProvince("ON").setLatitude(43.6532).setLongitude(-79.3832).build();
        Hotel hotel1 = new Hotel("Gamer Hotel", address_1, 5);

        user.addFavHotel(hotel1);
        user.removeFavHotel(hotel1);

        assertTrue(user.getFavHotelIds().isEmpty());
    }

}
