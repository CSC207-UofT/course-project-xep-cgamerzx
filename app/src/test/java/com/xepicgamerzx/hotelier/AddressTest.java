package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;

import org.junit.Test;

public class AddressTest {

    @Test
    public void SystemOutTest() {
        Address e = new Address("Testing Lane", "M5T2Y7", "123", "Toronto", "ON", 43.6532, -79.3832);
        System.out.println(e.toString());
    }

    @Test
    public void testGetStreetNumberName() {
        Address e = new Address("Testing Lane", "M5T2Y7", "123", "Toronto", "ON", 43.6532, -79.3832);
        String actual = "123 Testing Lane";
        assertEquals(e.getFullStreet(), actual);

    }

}
