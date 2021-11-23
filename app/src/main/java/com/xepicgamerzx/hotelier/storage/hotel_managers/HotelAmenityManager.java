package com.xepicgamerzx.hotelier.storage.hotel_managers;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenitiesEnum;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.storage.hotelier_database.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.HotelAmenityDao;

import java.util.List;

/**
 * A class to manage all the HotelAmenities in the database.
 */
public class HotelAmenityManager implements UniqueManager<HotelAmenity, HotelAmenitiesEnum> {
    private static volatile HotelAmenityManager INSTANCE;

    private final HotelierDatabase db;
    private final HotelAmenityDao hotelAmenityDao;

    private HotelAmenityManager(Application application) {
        db = HotelierDatabase.getDatabase(application);
        hotelAmenityDao = db.hotelAmenityDao();
    }

    private HotelAmenityManager(HotelierDatabase dbInstance) {
        db = dbInstance;
        hotelAmenityDao = db.hotelAmenityDao();
    }

    public static HotelAmenityManager getManager(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new HotelAmenityManager(application);
        }
        return INSTANCE;
    }

    public static HotelAmenityManager getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) {
            INSTANCE = new HotelAmenityManager(dbInstance);
        }
        return INSTANCE;
    }

    /**
     * Gets all instances of HotelAmenity in the database.
     *
     * @return List<HotelAmenity> saved in the database.
     */
    @Override
    public List<HotelAmenity> getAll() {
        return hotelAmenityDao.getAll();
    }

    /**
     * Updates HotelAmenity object(s) in the database.
     *
     * @param hotelAmenities HotelAmenity object(s) to be updated in the database.
     */
    @Override
    public void update(HotelAmenity... hotelAmenities) {
        hotelAmenityDao.update(hotelAmenities);
    }

    /**
     * Closes the manager instance if it is open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }

    /**
     * Inserts HotelAmenity objects to their database.
     *
     * @param object HotelAmenity object(s) to be inserted into the database.
     * @return null.
     */
    @Override
    public Void insert(HotelAmenity... object) {
        hotelAmenityDao.insert(object);
        return null;
    }

    /**
     * Gets objects with matching primary key IDs.
     *
     * @param ID String ID(s) acting as primary key to be searched for.
     * @return List<HotelAmenity> of objects with primary keys that match ID(s).
     */
    @Override
    public List<HotelAmenity> get(String... ID) {
        return hotelAmenityDao.getHotelAmenities(ID);
    }

    /**
     * Creates, inserts, and returns HotelAmenity object.
     *
     * @param amenity String name of the amenity to be created.
     * @return HotelAmenity created.
     */
    @Override
    public HotelAmenity create(String amenity) {
        HotelAmenity hotelAmenity = new HotelAmenity(amenity);
        insert(hotelAmenity);
        return hotelAmenity;
    }

    /**
     * Creates, inserts, and returns HotelAmenity object.
     *
     * @param amenity HotelAmenitiesEnum to be created
     * @return HotelAmenity created.
     */
    @Override
    public HotelAmenity create(HotelAmenitiesEnum amenity) {
        HotelAmenity hotelAmenity = new HotelAmenity(amenity.toString());
        insert(hotelAmenity);
        return hotelAmenity;
    }
}
