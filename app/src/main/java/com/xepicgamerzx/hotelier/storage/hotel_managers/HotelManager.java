package com.xepicgamerzx.hotelier.storage.hotel_managers;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewModel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.HotelDao;
import com.xepicgamerzx.hotelier.storage.user.UserManager;
import com.xepicgamerzx.hotelier.storage.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A class to manage all the hotels in the database.
 */
public class HotelManager implements Manager {
    private static volatile HotelManager INSTANCE;

    private final HotelierDatabase db;
    private final HotelDao hotelDao;
    private final RoomManager roomManager;
    //private final HotelAmenityManager amenityManager;

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
    public Hotel createHotel(String name, Address address, int starClass, List<HotelRoom> hotelRooms, List<HotelAmenity> hotelAmenities) {
        Hotel hotel = createHotel(name, address, starClass);

        for (HotelRoom hotelRoom : hotelRooms) {
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
        double centerLonCos = Math.cos(centerLon * Math.PI / 180);
        double centerLonSin = Math.sin(centerLon * Math.PI / 180);
        double centerLatCos = Math.cos(centerLat * Math.PI / 180);
        double centerLatSin = Math.sin(centerLat * Math.PI / 180);
        double cosDistance = Math.cos(distanceKM / 6371);

        return hotelDao.getHotelsInArea(centerLonCos, centerLonSin, centerLatCos, centerLatSin, cosDistance);
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
                cosDistance != null){

            return hotelDao.getHotelIdsInArea(centerLonCos, centerLonSin,
                    centerLatCos, centerLatSin,
                    cosDistance);
        }
        Log.e("Hotel Manager", "Failed to generate location data");
        return null;
    }

    public List<Hotel> getFavouriteHotels(User user) {
        ArrayList<Hotel> favourites = new ArrayList<>();
        List<Hotel> hotels = hotelDao.getAll();

        List<String> favHotelIds = user.getFavHotelIds();
        System.out.println(favHotelIds);

        for (Hotel hotel : hotels) {
            if (favHotelIds.contains(String.valueOf(hotel.hotelId))) {
                favourites.add(hotel);
            }
        }
        return favourites;
    }
    /**
     * Generates a list of HotelViewModel's with specifics
     */
    public List<HotelViewModel> generateHotelModel(Map<Hotel, List<HotelRoom>> hotelListMap) {
        List<HotelViewModel> hotelsView = new ArrayList<>();

        hotelListMap.forEach((hotel, rooms) ->
                hotelsView.add(new HotelViewModel(
                hotel.getName(),
                hotel.getAddress().getFullStreet(),
                roomManager.getPriceRange(hotel).get(0),
                roomManager.getNumberOfRooms(hotel),
                hotel,rooms
        )));

        return hotelsView;
    }
    /**
     * Generates a list of HotelViewModel's with specifics
     */
    public List<HotelViewModel> generateHotelModel(List<Hotel> hotels) {
        List<HotelViewModel> hotelsView = new ArrayList<>();

        for (Hotel hotel : hotels) {
            hotelsView.add(new HotelViewModel(
                    hotel.getName(),
                    hotel.getAddress().getFullStreet(),
                    roomManager.getPriceRange(hotel).get(0),
                    roomManager.getNumberOfRooms(hotel),
                    hotel, null
            ));
        }

        return hotelsView;
    }

    /**
     * Generates a list of HotelViewModel's for all hotels.
     */
    public List<HotelViewModel> generateHotelModel() {
        return generateHotelModel(db.hotelRoomMapDao().getAll());
    }


    /**
     * Generate list of HotelView models based on min capacity, location, and schedule
     *
     * @param capacity int min capacity
     * @param startTime long start time of schedule
     * @param endTime long end time of schedule
     * @param centerLat double location latitude
     * @param centerLon double location longitude
     * @return List<HotelViewModel> generated list of hotel view models
     */
    public List<HotelViewModel> generateHotelModel(int capacity, long startTime, long endTime, double centerLat, double centerLon) {
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
                cosDistance != null){
            hotelListMap = db.hotelRoomMapDao().getAvailableRooms(
                    capacity,
                    centerLonCos, centerLonSin,
                    centerLatCos, centerLatSin,
                    cosDistance,
                    startTime, endTime);
            return generateHotelModel(hotelListMap);
        } else {
            Log.e("Hotel Manager", "Failed to generate location data");
            return generateHotelModel(capacity, startTime, endTime);
        }

    }

    /**
     * Generate list of HotelView models based on min capacity and location
     *
     * @param capacity int min capacity
     * @param centerLat double location latitude
     * @param centerLon double location longitude
     * @return List<HotelViewModel> generated list of hotel view models
     */
    public List<HotelViewModel> generateHotelModel(int capacity, double centerLat, double centerLon) {
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
                cosDistance != null){
            hotelListMap = db.hotelRoomMapDao().getAvailableRooms(
                    capacity,
                    centerLonCos, centerLonSin,
                    centerLatCos, centerLatSin,
                    cosDistance);
            return generateHotelModel(hotelListMap);
        } else {
            Log.e("Hotel Manager", "Failed to generate location data");
            return generateHotelModel(capacity);
        }
    }

    /**
     * Generate list of HotelView models based on min capacity and schedule
     *
     * @param capacity int min capacity
     * @param startTime long start time of schedule
     * @param endTime long end time of schedule
     * @return List<HotelViewModel> generated list of hotel view models
     */
    public List<HotelViewModel> generateHotelModel(int capacity, long startTime, long endTime) {
        Map<Hotel, List<HotelRoom>> hotelListMap = db.hotelRoomMapDao().getAvailableRooms(startTime, endTime, capacity);
        return generateHotelModel(hotelListMap);
    }

    /**
     * Generate list of HotelView models based on min capacity
     *
     * @param capacity Min capacity of rooms
     * @return List<HotelViewModel> generated list of hotel view models
     */
    public List<HotelViewModel> generateHotelModel(int capacity) {
        Map<Hotel, List<HotelRoom>> hotelListMap = db.hotelRoomMapDao().getAvailableRooms(capacity);
        return generateHotelModel(hotelListMap);
    }

    /**
     * Generate spherical coordinate locations based on cartesian coordinates
     *
     * @param centerLat double cartesian latitude
     * @param centerLon double cartesian longitude
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

    public List<Hotel> getAllHotels() {
        return hotelDao.getAll();

    }
}
