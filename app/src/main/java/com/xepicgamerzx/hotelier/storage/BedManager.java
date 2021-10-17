package com.xepicgamerzx.hotelier.storage;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.Room;

import java.util.ArrayList;
import java.util.List;

public class BedManager {

    public ArrayList<Bed> createBeds(int numberOfBeds, String size) {
        ArrayList<Bed> allBeds = new ArrayList<Bed>();
        for (int i = 0; i < numberOfBeds; i++) {
            Bed bed = new Bed(size);
            allBeds.add(bed);
        }

        return allBeds;
    }

    public void setRoomForAllBeds(Room room, List<Bed> beds) {
        for (Bed bed : beds) {
            bed.setRoom(room);
        }
    }


}
