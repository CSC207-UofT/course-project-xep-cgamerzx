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

    @Test
    public void testGetPostalCode() {
        Address e = new AddressBuilder().setStreetName("Testing Lane").setPostalCode("M5T2Y7").setStreetNumber("123").setCity("Toronto").setProvince("ON").setLatitude(43.6532).setLongitude(-79.3832).build();
        String actual = "M5T2Y7";
        assertEquals(e.getPostalCode(), actual);

    }

    @Test
    public void testGetStreetNumber() {
        Address e = new AddressBuilder().setStreetName("Testing Lane").setPostalCode("M5T2Y7").setStreetNumber("123").setCity("Toronto").setProvince("ON").setLatitude(43.6532).setLongitude(-79.3832).build();
        String actual = "123";
        assertEquals(e.getStreetNumber(), actual);

    }

    @Test
    public void testGetCity() {
        Address e = new AddressBuilder().setStreetName("Testing Lane").setPostalCode("M5T2Y7").setStreetNumber("123").setCity("Toronto").setProvince("ON").setLatitude(43.6532).setLongitude(-79.3832).build();
        String actual = "Toronto";
        assertEquals(e.getCity(), actual);

    }

    @Test
    public void testGetProvince() {
        Address e = new AddressBuilder().setStreetName("Testing Lane").setPostalCode("M5T2Y7").setStreetNumber("123").setCity("Toronto").setProvince("ON").setLatitude(43.6532).setLongitude(-79.3832).build();
        String actual = "ON";
        assertEquals(e.getProvince(), actual);

    }

}
