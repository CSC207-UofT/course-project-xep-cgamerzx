package com.xepicgamerzx.hotelier.storage;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.BedSize;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.dao.BedDao;

import java.util.List;

public class BedManager implements StringManager<Bed> {
    private static volatile BedManager INSTANCE;

    private final HotelierDatabase db;
    private final BedDao bedDao;

    private BedManager(Application application) {
        db = HotelierDatabase.getDatabase(application);
        bedDao = db.bedDao();
    }

    private BedManager(HotelierDatabase dbInstance) {
        db = dbInstance;
        bedDao = db.bedDao();
    }

    public static BedManager getManager(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new BedManager(application);
        }
        return INSTANCE;
    }

    public static BedManager getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) {
            INSTANCE = new BedManager(dbInstance);
        }
        return INSTANCE;
    }


    public Bed createBed(String bedSize) {
        Bed bed = new Bed(bedSize);
        insert(bed);
        return bed;
    }

    public Bed createBed(BedSize bedSize) {
        Bed bed = new Bed(bedSize);
        insert(bed);
        return bed;
    }

    @Override
    public void insert(Bed... bed) {
        bedDao.insertBeds(bed);
    }

    public void addRoomToBed(Bed bed, HotelRoom hotelRoom) {

    }

    /**
     * Updates Bed object(s) in the database.
     *
     * @param bed Bed object(s) to be updated in the database.
     */
    @Override
    public void update(Bed... bed) {
        bedDao.updateBed(bed);
    }

    @Override
    public List<Bed> get(String... ID) {
        return bedDao.getBeds(ID);
    }

    @Override
    public List<Bed> getAll() {
        return bedDao.getAllBeds();
    }

    /**
     * Closes the manager if already open.
     */
    @Override
    public void close(){
        INSTANCE = null;
    }
}
