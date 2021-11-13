package com.xepicgamerzx.hotelier;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.OldObjects.AddressOld;
import com.xepicgamerzx.hotelier.objects.OldObjects.BedOld;
import com.xepicgamerzx.hotelier.objects.OldObjects.HotelOld;
import com.xepicgamerzx.hotelier.objects.OldObjects.OldRoom;
import com.xepicgamerzx.hotelier.storage.firebase.HotelOldDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class FirebaseTest {

    @Test
    public void hotelTest() {
        AddressOld address = new AddressOld("Testing Lane",
                "M5T2Y7",
                "123",
                "Toronto",
                "ON",
                43.6532,
                79.3832);
        BedOld bed1 = new BedOld("Queen");

        List<BedOld> beds = new ArrayList<BedOld>();
        beds.add(bed1);

        OldRoom room = new OldRoom(
                1634281932,
                1636960332,
                4, beds, 250);

        HashMap<String, OldRoom> rooms = new HashMap<String, OldRoom>();
        rooms.put("Room 1", room);

        HotelOld h = new HotelOld("Testing", address, rooms);
        room.setHotel(h);
        //        bed1.setRoom(room); this causes stack over flow error.

        HotelOldDAO dao = new HotelOldDAO();

        dao.add(h);
    }
}
