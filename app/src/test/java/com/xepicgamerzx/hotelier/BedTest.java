package com.xepicgamerzx.hotelier;

import org.junit.Test;

import static org.junit.Assert.*;

import com.xepicgamerzx.hotelier.objects.Bed;

public class BedTest {

    @Test
    public void testBedWithNoRoomReference() {
        Bed bed = new Bed("Queen");
        assertNull(bed.room);
    }
}
