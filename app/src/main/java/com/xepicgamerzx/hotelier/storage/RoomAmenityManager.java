package com.xepicgamerzx.hotelier.storage;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.RoomAmenitiesEnum;
import com.xepicgamerzx.hotelier.objects.RoomAmenity;
import com.xepicgamerzx.hotelier.storage.dao.RoomAmenityDao;

import java.util.List;

/**
 * A class to manage all the RoomAmenities in the database.
 */
public class RoomAmenityManager implements Manager<RoomAmenity, String, Void> {
    private static volatile RoomAmenityManager INSTANCE;

    private final HotelierDatabase db;
    private final RoomAmenityDao roomAmenityDao;

    private RoomAmenityManager(Application application) {
        db = HotelierDatabase.getDatabase(application);
        roomAmenityDao = db.roomAmenityDao();
    }

    private RoomAmenityManager(HotelierDatabase dbInstance) {
        db = dbInstance;
        roomAmenityDao = db.roomAmenityDao();
    }

    public static RoomAmenityManager getManager(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new RoomAmenityManager(application);
        }
        return INSTANCE;
    }

    public static RoomAmenityManager getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) {
            INSTANCE = new RoomAmenityManager(dbInstance);
        }
        return INSTANCE;
    }

    /**
     * Gets all instances of RoomAmenity in the database.
     *
     * @return ListRoomAmenity saved in the database.
     */
    @Override
    public List<RoomAmenity> getAll() {
        return roomAmenityDao.getAllRoomAmenities();
    }

    /**
     * Updates RoomAmenity object(s) in the database.
     *
     * @param object RoomAmenity object(s) to be updated in the database.
     */
    @Override
    public void update(RoomAmenity... object) {
        roomAmenityDao.updateRoomAmenities(object);
    }

    /**
     * Closes the manager instance if it is open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }

    /**
     * Inserts RoomAmenity objects to their database.
     *
     * @param object RoomAmenity object(s) to be inserted into the database.
     * @return null.
     */
    @Override
    public Void insert(RoomAmenity... object) {
        roomAmenityDao.insertRoomAmenities(object);
        return null;
    }

    /**
     * Gets objects with matching primary key IDs.
     *
     * @param ID String ID(s) acting as primary key to be searched for.
     * @return List<RoomAmenity> of objects with primary keys that match ID(s).
     */
    @Override
    public List<RoomAmenity> get(String... ID) {
        return roomAmenityDao.getRoomAmenities(ID);
    }

    /**
     * Creates, inserts, and returns RoomAmenity object.
     *
     * @param amenity String name of the amenity to be created.
     * @return RoomAmenity created.
     */
    public RoomAmenity create(String amenity) {
        RoomAmenity roomAmenity = new RoomAmenity(amenity);
        insert(roomAmenity);
        return roomAmenity;
    }

    /**
     * Creates, inserts, and returns RoomAmenity object.
     *
     * @param amenity RoomAmenity to be created
     * @return RoomAmenity created.
     */
    public RoomAmenity create(RoomAmenitiesEnum amenity) {
        RoomAmenity roomAmenity = new RoomAmenity(amenity.toString());
        insert(roomAmenity);
        return roomAmenity;
    }
}
