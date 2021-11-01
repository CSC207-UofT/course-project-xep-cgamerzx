package com.xepicgamerzx.hotelier.storage;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.BedSize;
import com.xepicgamerzx.hotelier.objects.BedsRoomCrossRef;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.dao.BedDao;
import com.xepicgamerzx.hotelier.storage.dao.BedRoomCrossDao;

import java.util.List;

public class BedManager implements Manager<Bed, String, Void> {
    private static volatile BedManager INSTANCE;

    private final HotelierDatabase db;
    private final BedDao bedDao;
    private final BedRoomCrossDao bedRoomCrossDao;

    private BedManager(Application application) {
        db = HotelierDatabase.getDatabase(application);
        bedDao = db.bedDao();
        bedRoomCrossDao = db.bedRoomCrossDao();
    }

    private BedManager(HotelierDatabase dbInstance) {
        db = dbInstance;
        bedDao = db.bedDao();
        bedRoomCrossDao = db.bedRoomCrossDao();
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


    public Bed create(String bedSize) {
        Bed bed = new Bed(bedSize);
        insert(bed);
        return bed;
    }

    public Bed create(BedSize bedSize) {
        Bed bed = new Bed(bedSize);
        insert(bed);
        return bed;
    }

    /**
     * Inserts the beds(s) to the Hotel database.
     *
     * @param bed Bed object(s) to be saved.
     * @return null
     */
    @Override
    public Void insert(Bed... bed) {
        bedDao.insertBeds(bed);
        return null;
    }

    /**
     * Create and insert relationship between Bed and HotelRoom into BedRoomCrossRef database.
     *
     * @param bed Bed being assigned to a hotel room.
     * @param hotelRoom HotelRoom being assigned beds.
     * @param bedCount Int number of Bed type associated with the room.
     */
    public void addBedToRoom(Bed bed, HotelRoom hotelRoom, int bedCount) {
        BedsRoomCrossRef bedsRoomCrossRef = new BedsRoomCrossRef(hotelRoom, bed, bedCount);
        bedRoomCrossDao.insertBedRoomCrossRef(bedsRoomCrossRef);
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

    /**
     * Get beds with matching bed IDs.
     *
     * @param bedID String bedIDs to be used as search keys.
     * @return List of Beds with matching bedIDs.
     */
    @Override
    public List<Bed> get(String... bedID) {
        return bedDao.getBeds(bedID);
    }

    /**
     * Gets all beds in the Bed database.
     *
     * @return List of the Beds in the Bed database.
     */
    @Override
    public List<Bed> getAll() {
        return bedDao.getAllBeds();
    }

    /**
     * Closes the manager if already open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }
}
