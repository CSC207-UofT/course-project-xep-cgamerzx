package com.xepicgamerzx.hotelier.storage.hotel_reference_managers;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.cross_reference_objects.HotelAmenitiesCrossRef;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.HotelAmenitiesCrossDao;
import com.xepicgamerzx.hotelier.storage.dao.HotelAmenityDao;
import com.xepicgamerzx.hotelier.storage.dao.HotelDao;

import java.util.List;

public class HotelAmenitiesCrossManager implements CrossManager<HotelAmenitiesCrossRef, Hotel, HotelAmenity> {
    private static volatile HotelAmenitiesCrossManager INSTANCE;

    private final HotelAmenitiesCrossDao hotelAmenitiesCrossDao;
    private final HotelDao hotelDao;
    private final HotelAmenityDao hotelAmenityDao;


    private HotelAmenitiesCrossManager(HotelierDatabase dbInstance) {
        hotelAmenitiesCrossDao = dbInstance.hotelAmenitiesCrossDao();
        hotelDao = dbInstance.hotelDao();
        hotelAmenityDao = dbInstance.hotelAmenityDao();
    }

    public static HotelAmenitiesCrossManager getManager(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new HotelAmenitiesCrossManager(HotelierDatabase.getDatabase(application));
        }
        return INSTANCE;
    }

    public static HotelAmenitiesCrossManager getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) {
            INSTANCE = new HotelAmenitiesCrossManager(dbInstance);
        }
        return INSTANCE;
    }


    /**
     * Create and insert relationship between Hotel and HotelAmenity into HotelAmenitiesCrossRef database.
     *
     * @param hotel        Hotel being assigned to hotelAmenity.
     * @param hotelAmenity HotelAmenity being assigned to Hotel.
     * @return HotelAmenitiesCrossRef created.
     */
    @Override
    public HotelAmenitiesCrossRef createRelationship(Hotel hotel, HotelAmenity hotelAmenity) {
        HotelAmenitiesCrossRef crossRef = new HotelAmenitiesCrossRef(hotel, hotelAmenity);
        hotelAmenitiesCrossDao.insert(crossRef);
        return crossRef;
    }

    /**
     * Get all Hotel associated with HotelAmenity.
     *
     * @param hotelAmenity HotelAmenity key to search with.
     * @return List<Hotel> associated with HotelAmenity.
     */
    @Override
    public List<Hotel> getRelated(HotelAmenity hotelAmenity) {
        List<Long> ids = hotelAmenitiesCrossDao.getWith(hotelAmenity.getUniqueId());
        return hotelDao.getIdMatch(ids.toArray(new Long[0]));
    }

    /**
     * Get all HotelAmenity associated with Hotel.
     *
     * @param hotel Hotel key to search with.
     * @return List<HotelAmenity> associated with Hotel.
     */
    @Override
    public List<HotelAmenity> getRelated(Hotel hotel) {
        List<String> ids = hotelAmenitiesCrossDao.getWith(hotel.hotelId);
        return hotelAmenityDao.getIdMatch(ids.toArray(new String[0]));
    }

    /**
     * Get all HotelAmenitiesCrossRef cross references associated with HotelAmenity
     *
     * @param hotelAmenity HotelAmenity key to search with.
     * @return List<HotelAmenitiesCrossRef> cross references associated with HotelAmenity
     */
    @Override
    public List<HotelAmenitiesCrossRef> getRelatedCross(HotelAmenity hotelAmenity) {
        return hotelAmenitiesCrossDao.getCrossWith(hotelAmenity.getUniqueId());
    }

    /**
     * Get all HotelAmenitiesCrossRef cross references associated with Hotel
     *
     * @param hotel Hotel key to search with.
     * @return List<HotelAmenitiesCrossRef> cross references associated with Hotel
     */
    @Override
    public List<HotelAmenitiesCrossRef> getRelatedCross(Hotel hotel) {
        return hotelAmenitiesCrossDao.getCrossWith(hotel.hotelId);
    }

    /**
     * Closes the manager instance if it is open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }
}
