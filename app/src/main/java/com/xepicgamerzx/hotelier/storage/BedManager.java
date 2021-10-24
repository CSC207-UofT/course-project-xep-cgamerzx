package com.xepicgamerzx.hotelier.storage;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.BedSize;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.dao.BedDao;
import com.xepicgamerzx.hotelier.storage.dao.HotelDao;

public class BedManager {
    private HotelierDatabase db;
    private BedDao bedDao;

    public BedManager(Application application){
        db = HotelierDatabase.getDatabase(application);
        bedDao = db.bedDao();
    }

    public Bed createBed(String bedSize){
        Bed bed = new Bed(bedSize);
        insertBed(bed);
        return bed;
    }

    public Bed createBed(BedSize bedSize){
        Bed bed = new Bed(bedSize);
        insertBed(bed);
        return bed;
    }

    private void insertBed(Bed bed){
        bedDao.insertBeds(bed);
    }

    public void addRoomToBed(Bed bed, HotelRoom hotelRoom){

    }
}
