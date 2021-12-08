package com.xepicgamerzx.hotelier.storage.hotel_managers;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.HotelDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A class to manage all the hotels in the database.
 */
public class HotelManager implements Manager {
    private static volatile HotelManager INSTANCE;

    private final HotelierDatabase db;
    private final HotelDao hotelDao;
    private final RoomManager roomManager;

    private HotelManager(Application application) {
        db = HotelierDatabase.getDatabase(application);
        hotelDao = db.hotelDao();
        roomManager = RoomManager.getManager(application);
    }

    private HotelManager(HotelierDatabase dbInstance) {
        db = dbInstance;
        hotelDao = db.hotelDao();
        roomManager = RoomManager.getManager(dbInstance);
    }

    public static HotelManager getManager(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new HotelManager(application);
        }

        return INSTANCE;
    }

    public static HotelManager getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) {
            INSTANCE = new HotelManager(dbInstance);
        }

        return INSTANCE;
    }

    /**
     * Creates hotel object and inserts it to the database. (No rooms)
     *
     * @param name      String name of the hotel.
     * @param address   Address object associated with the hotel.
     * @param starClass Int star class of the hotel
     * @return Hotel object created.
     */
    @NonNull
    public Hotel createHotel(String name, Address address, int starClass) {
        Hotel hotel = new Hotel(name, address, starClass);
        Long[] id = hotelDao.insert(hotel).toArray(new Long[0]);
        return hotelDao.getIdMatch(id).get(0);
    }

    /**
     * Creates hotel object and inserts it to the database. Also associates a list of hotel rooms
     * with the hotel object and inserts these associations to the HotelRoom database.
     *
     * @param name       String name of the hotel.
     * @param address    Address object associated with the hotel.
     * @param starClass  Int star class of the hotel
     * @param hotelRooms List of HotelRooms to be associated with the Hotel object.
     * @return Hotel object created.
     */
    @NonNull
    public Hotel createHotel(String name, Address address, int starClass, List<HotelRoom> hotelRooms) {
        Hotel hotel = createHotel(name, address, starClass);

        for (HotelRoom hotelRoom : hotelRooms) {
            roomManager.setHotelID(hotel, hotelRoom);
        }
        return hotel;
    }

    // With amenities

    /**
     * Creates hotel object and inserts it to the database. Also associates a list of hotel rooms
     * with the hotel object and inserts these associations to the HotelRoom database.
     *
     * @param name       String name of the hotel.
     * @param address    Address object associated with the hotel.
     * @param starClass  Int star class of the hotel
     * @param hotelRooms List of HotelRooms to be associated with the Hotel object.
     * @return Hotel object created.
     */
    @NonNull
    public Hotel createHotel(String name, Address address, int starClass, List<Long> hotelRooms, List<String> hotelAmenities) {
        Hotel hotel = createHotel(name, address, starClass);

        for (long hotelRoom : hotelRooms) {
            roomManager.setHotelID(hotel, hotelRoom);
        }

        /*for (HotelAmenity hotelAmenity : hotelAmenities) {
            amenityManager
        }*/

        return hotel;
    }


    /**
     * Get all hotels approximately within 50KM of a given location.
     *
     * @param centerLat double latitude of center of query
     * @param centerLon double longitude of center of query
     * @return List<Hotel> all hotels in the approximately in the search area.
     */
    public List<Hotel> getHotelsInArea(double centerLat, double centerLon) {
        return getHotelsInArea(centerLat, centerLon, 50.0);
    }

    /**
     * Get all hotels approximately in the radius around a given location.
     *
     * @param centerLat  double latitude of center of query
     * @param centerLon  double longitude of center of query
     * @param distanceKM double radius of search area in kilometers
     * @return List<Hotel> all hotels in the approximately in the search area.
     */
    public List<Hotel> getHotelsInArea(double centerLat, double centerLon, double distanceKM) {
        Map<String, Double> locationMap = convertLatLon(centerLat, centerLon, distanceKM);

        Double centerLonCos = locationMap.get("centerLonCos");
        Double centerLonSin = locationMap.get("centerLonSin");
        Double centerLatCos = locationMap.get("centerLatCos");
        Double centerLatSin = locationMap.get("centerLatSin");
        Double cosDistance = locationMap.get("cosDistance");

        if (centerLonCos != null &&
                centerLonSin != null &&
                centerLatCos != null &&
                centerLatSin != null &&
                cosDistance != null) {

            return hotelDao.getHotelsInArea(centerLonCos, centerLonSin, centerLatCos, centerLatSin, cosDistance);
        }
        Log.e("Hotel Manager", "Failed to generate location data");
        return null;
    }

    /**
     * Get all hotels approximately within 50KM of a given location.
     *
     * @param centerLat double latitude of center of query
     * @param centerLon double longitude of center of query
     * @return List<Long> all hotels in the approximately in the search area.
     */
    public List<Long> getHotelIdsInArea(double centerLat, double centerLon) {
        return getHotelIdsInArea(centerLat, centerLon, 50.0);
    }


    /**
     * Get all hotels approximately in the radius around a given location.
     *
     * @param centerLat  double latitude of center of query
     * @param centerLon  double longitude of center of query
     * @param distanceKM double radius of search area in kilometers
     * @return List<Long> all hotels in the approximately in the search area.
     */
    public List<Long> getHotelIdsInArea(double centerLat, double centerLon, double distanceKM) {
        Map<String, Double> locationMap = convertLatLon(centerLat, centerLon, distanceKM);

        Double centerLonCos = locationMap.get("centerLonCos");
        Double centerLonSin = locationMap.get("centerLonSin");
        Double centerLatCos = locationMap.get("centerLatCos");
        Double centerLatSin = locationMap.get("centerLatSin");
        Double cosDistance = locationMap.get("cosDistance");

        if (centerLonCos != null &&
                centerLonSin != null &&
                centerLatCos != null &&
                centerLatSin != null &&
                cosDistance != null) {

            return hotelDao.getHotelIdsInArea(centerLonCos, centerLonSin,
                    centerLatCos, centerLatSin,
                    cosDistance);
        }
        Log.e("Hotel Manager", "Failed to generate location data");
        return null;
    }

        /**
         * Generate spherical coordinate locations based on cartesian coordinates
         *
         * @param centerLat  double cartesian latitude
         * @param centerLon  double cartesian longitude
         * @param distanceKM double distance in kilometers
         * @return Map<String, Double> with centerLonCos, centerLonSin, centerLatCos, centerLatSin, cosDistance
         */
    private Map<String, Double> convertLatLon(double centerLat, double centerLon, double distanceKM) {
        Map<String, Double> map = new HashMap<>();

        map.put("centerLonCos", Math.cos(centerLon * Math.PI / 180));
        map.put("centerLonSin", Math.sin(centerLon * Math.PI / 180));
        map.put("centerLatCos", Math.cos(centerLat * Math.PI / 180));
        map.put("centerLatSin", Math.sin(centerLat * Math.PI / 180));
        map.put("cosDistance", Math.cos(distanceKM / 6371));

        return map;
    }

    /**
     * Closes the manager if already open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }
}
