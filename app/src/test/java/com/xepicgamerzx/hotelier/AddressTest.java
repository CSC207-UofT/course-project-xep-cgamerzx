package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.AddressBuilder;

import org.junit.Test;

public class AddressTest {

    @Test
    public void SystemOutTest() {
        Address e = new AddressBuilder().setStreetName("Testing Lane").setPostalCode("M5T2Y7").setStreetNumber("123").setCity("Toronto").setProvince("ON").setLatitude(43.6532).setLongitude(-79.3832).build();
        System.out.println(e.toString());
    }

    @Test
    public void testGetStreetNumberName() {
        Address e = new AddressBuilder().setStreetName("Testing Lane").setPostalCode("M5T2Y7").setStreetNumber("123").setCity("Toronto").setProvince("ON").setLatitude(43.6532).setLongitude(-79.3832).build();
        String actual = "123 Testing Lane";
        assertEquals(e.getFullStreet(), actual);

    }

}
