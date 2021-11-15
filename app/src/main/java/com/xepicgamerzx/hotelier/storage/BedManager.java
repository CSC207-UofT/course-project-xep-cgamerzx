package com.xepicgamerzx.hotelier.storage;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.BedSizeEnum;
import com.xepicgamerzx.hotelier.storage.dao.BedDao;
import com.xepicgamerzx.hotelier.storage.dao.BedRoomCrossDao;

import java.util.List;

/**
 * A class to manage all the Beds in the database.
 */
public class BedManager implements UniqueManager<Bed, BedSizeEnum> {
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

    /**
     * Creates, inserts, and returns Bed object.
     *
     * @param bedSize String name of the bed to be created.
     * @return Bed created.
     */
    @Override
    public Bed create(String bedSize) {
        Bed bed = new Bed(bedSize);
        insert(bed);
        return bed;
    }

    /**
     * Creates, inserts, and returns Bed object.
     *
     * @param bedSizeEnum BedSize to be created.
     * @return Bed created.
     */
    @Override
    public Bed create(BedSizeEnum bedSizeEnum) {
        Bed bed = new Bed(bedSizeEnum.toString());
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
        bedDao.insert(bed);
        return null;
    }

    /**
     * Updates Bed object(s) in the database.
     *
     * @param bed Bed object(s) to be updated in the database.
     */
    @Override
    public void update(Bed... bed) {
        bedDao.update(bed);
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
        return bedDao.getAll();
    }

    /**
     * Closes the manager if already open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }
}
