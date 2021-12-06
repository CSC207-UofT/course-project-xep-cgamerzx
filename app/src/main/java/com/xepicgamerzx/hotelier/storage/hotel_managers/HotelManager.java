package com.xepicgamerzx.hotelier.storage.hotel_managers;

import android.app.Application;

import androidx.annotation.NonNull;

import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewModel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.HotelDao;
import com.xepicgamerzx.hotelier.storage.user.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to manage all the hotels in the database.
 */
public class HotelManager implements Manager<Hotel, Long[]> {
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
        double centerLonCos = Math.cos(centerLon * Math.PI / 180);
        double centerLonSin = Math.sin(centerLon * Math.PI / 180);
        double centerLatCos = Math.cos(centerLat * Math.PI / 180);
        double centerLatSin = Math.sin(centerLat * Math.PI / 180);
        double cosDistance = Math.cos(distanceKM / 6371);

        return hotelDao.getHotelIdsInArea(centerLonCos, centerLonSin, centerLatCos, centerLatSin, cosDistance);
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
    public List<HotelViewModel> generateHotelModel(List<Hotel> hotels) {
        List<HotelViewModel> hotelsView = new ArrayList<>();

        for (Hotel hotel : hotels) {
            hotelsView.add(new HotelViewModel(
                    hotel.getName(),
                    hotel.getAddress().getFullStreet(),
                    roomManager.getPriceRange(hotel).get(0),
                    roomManager.getNumberOfRooms(hotel),
                    hotel
            ));
        }

        return hotelsView;
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
