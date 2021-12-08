package com.xepicgamerzx.hotelier.storage.hotel_reference_managers;

import android.app.Application;
import android.util.Log;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;
import com.xepicgamerzx.hotelier.storage.hotel_managers.Manager;
import com.xepicgamerzx.hotelier.storage.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelRoomMapManager implements Manager {
    private static volatile HotelRoomMapManager INSTANCE;

    private final HotelierDatabase db;
    private final Manage manage;

    private HotelRoomMapManager(Application application) {
        db = HotelierDatabase.getDatabase(application);
        manage = Manage.getManager(application);
    }

    private HotelRoomMapManager(HotelierDatabase dbInstance) {
        db = dbInstance;
        manage = Manage.getManager(dbInstance);
    }

    public static HotelRoomMapManager getManager(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new HotelRoomMapManager(application);
        }

        return INSTANCE;
    }

    public static HotelRoomMapManager getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) {
            INSTANCE = new HotelRoomMapManager(dbInstance);
        }

        return INSTANCE;
    }

    /**
     * Generates Map<Hotel, List<HotelRoom>> with specifics
     */
    public Map<Hotel, List<HotelRoom>> getAvailableRooms() {
        return db.hotelRoomMapDao().getAll();
    }

    /**
     * Generate Map<Hotel, List<HotelRoom>> based on min capacity, location, and schedule
     *
     * @param capacity  int min capacity
     * @param startTime long start time of schedule
     * @param endTime   long end time of schedule
     * @param centerLat double location latitude
     * @param centerLon double location longitude
     * @return Map<Hotel, List<HotelRoom>> generated list of hotel view models
     */
    public Map<Hotel, List<HotelRoom>> getAvailableRooms(int capacity, long startTime, long endTime, double centerLat, double centerLon) {
        Map<String, Double> locationMap = convertLatLon(centerLat, centerLon, 50);
        Map<Hotel, List<HotelRoom>> hotelListMap;

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
            hotelListMap = db.hotelRoomMapDao().getAvailableRooms(
                    capacity,
                    centerLonCos, centerLonSin,
                    centerLatCos, centerLatSin,
                    cosDistance,
                    startTime, endTime);
            return hotelListMap;
        } else {
            Log.e("Hotel Room Map Manager", "Failed to generate location data");
            return getAvailableRooms(capacity, startTime, endTime);
        }

    }

    /**
     * Generate Map<Hotel, List<HotelRoom>> based on min capacity and location
     *
     * @param capacity  int min capacity
     * @param centerLat double location latitude
     * @param centerLon double location longitude
     * @return Map<Hotel, List<HotelRoom>> generated list of hotel view models
     */
    public Map<Hotel, List<HotelRoom>> getAvailableRooms(int capacity, double centerLat, double centerLon) {
        Map<String, Double> locationMap = convertLatLon(centerLat, centerLon, 50);
        Map<Hotel, List<HotelRoom>> hotelListMap;

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
            hotelListMap = db.hotelRoomMapDao().getAvailableRooms(
                    capacity,
                    centerLonCos, centerLonSin,
                    centerLatCos, centerLatSin,
                    cosDistance);
            return hotelListMap;
        } else {
            Log.e("Hotel Room Map Manager", "Failed to generate location data");
            return getAvailableRooms(capacity);
        }
    }

    /**
     * Generate Map<Hotel, List<HotelRoom>> based on min capacity and schedule
     *
     * @param capacity  int min capacity
     * @param startTime long start time of schedule
     * @param endTime   long end time of schedule
     * @return Map<Hotel, List<HotelRoom>> generated list of hotel view models
     */
    public Map<Hotel, List<HotelRoom>> getAvailableRooms(int capacity, long startTime, long endTime) {
        return db.hotelRoomMapDao().getAvailableRooms(startTime, endTime, capacity);
    }

    /**
     * Generate Map<Hotel, List<HotelRoom>> based on min capacity
     *
     * @param capacity Min capacity of rooms
     * @return Map<Hotel, List<HotelRoom>> generated list of hotel view models
     */
    public Map<Hotel, List<HotelRoom>> getAvailableRooms(int capacity) {
        return db.hotelRoomMapDao().getAvailableRooms(capacity);
    }

    public Map<Hotel, List<HotelRoom>> getFavourites(User user){
        Long[] hotelIds = user.getFavHotelIdsL().toArray(new Long[0]);
        return db.hotelRoomMapDao().getHotelWithId(hotelIds);
    }

    public Map<Hotel, List<HotelRoom>> getFavourites(){
        Long[] hotelIds = manage.userManager.getUser().getFavHotelIdsL().toArray(new Long[0]);
        return db.hotelRoomMapDao().getHotelWithId(hotelIds);
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
