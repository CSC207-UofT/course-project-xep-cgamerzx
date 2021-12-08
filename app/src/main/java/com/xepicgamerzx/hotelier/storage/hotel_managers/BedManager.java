package com.xepicgamerzx.hotelier.storage.hotel_managers;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.hotel_objects.BedSizeEnum;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.BedDao;

/**
 * A class to manage all the Beds in the database.
 */
public class BedManager implements UniqueManager<Bed, BedSizeEnum> {
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

    /**
     * Creates, inserts, and returns Bed object.
     *
     * @param bedSize String name of the bed to be created.
     * @return Bed created.
     */
    @Override
    public Bed create(String bedSize) {
        Bed bed = new Bed(bedSize);
        bedDao.insert(bed);
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
        bedDao.insert(bed);
        return bed;
    }

    /**
     * Create unique object and insert it into database.
     *
     * @param id String unique ID of object.
     * @return String Id of unique object created
     */
    @Override
    public String createId(String id) {
        return create(id).getUniqueId();
    }

    /**
     * Create unique object and insert it into database.
     *
     * @param id <E> LabeledEnum of unique object to be created.
     * @return String Id of unique object created
     */
    @Override
    public String createId(BedSizeEnum id) {
        return createId(id.toString());
    }

    /**
     * Closes the manager if already open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }
}
