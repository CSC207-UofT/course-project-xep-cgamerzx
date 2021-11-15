package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertEquals;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;

import org.junit.Test;
public class BedTest {
    @Test
    public void testGetBedId() {
        Bed bed = new Bed("Queen");
        assertEquals(bed.getUniqueId(), "Queen");
    }

}
