package com.xepicgamerzx.hotelier.storage.hotel_managers;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenitiesEnum;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.HotelAmenityDao;

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
     * Creates, inserts, and returns HotelAmenity object.
     *
     * @param amenity String name of the amenity to be created.
     * @return HotelAmenity created.
     */
    @Override
    public HotelAmenity create(String amenity) {
        HotelAmenity hotelAmenity = new HotelAmenity(amenity);
        hotelAmenityDao.insert(hotelAmenity);
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
        hotelAmenityDao.insert(hotelAmenity);
        return hotelAmenity;
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
    public String createId(HotelAmenitiesEnum id) {
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
