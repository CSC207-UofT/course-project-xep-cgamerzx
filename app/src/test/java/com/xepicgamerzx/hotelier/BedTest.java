package com.xepicgamerzx.hotelier;

import com.xepicgamerzx.hotelier.objects.Bed;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class BedTest {
    @Test
    public void testGetBedId() {
        Bed bed = new Bed("Queen");
        assertEquals(bed.getBedID(), "Queen");
    }

}
