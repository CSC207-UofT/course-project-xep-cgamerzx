package com.xepicgamerzx.hotelier;

import static org.junit.Assert.assertNull;

import com.xepicgamerzx.hotelier.objects.Bed;

import org.junit.Test;

public class BedTest {

    @Test
    public void testBedWithNoRoomReference() {
        Bed bed = new Bed("Queen");
        assertNull(bed.room);
    }
}
