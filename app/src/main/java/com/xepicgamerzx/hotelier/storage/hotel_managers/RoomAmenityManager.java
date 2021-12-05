package com.xepicgamerzx.hotelier.storage.hotel_managers;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomAmenitiesEnum;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.hotel_objects.RoomAmenity;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.RoomAmenitiesCrossDao;
import com.xepicgamerzx.hotelier.storage.dao.RoomAmenityDao;

import java.util.List;

/**
 * A class to manage all the RoomAmenities in the database.
 */
public class RoomAmenityManager implements UniqueManager<RoomAmenity, RoomAmenitiesEnum> {
    private static volatile RoomAmenityManager INSTANCE;

    private final HotelierDatabase db;
    private final RoomAmenityDao roomAmenityDao;
    private final RoomAmenitiesCrossDao roomAmenitiesCrossDao;

    private RoomAmenityManager(Application application) {
        db = HotelierDatabase.getDatabase(application);
        roomAmenityDao = db.roomAmenityDao();
        roomAmenitiesCrossDao = db.roomAmenitiesCrossDao();
    }

    private RoomAmenityManager(HotelierDatabase dbInstance) {
        db = dbInstance;
        roomAmenityDao = db.roomAmenityDao();
        roomAmenitiesCrossDao = db.roomAmenitiesCrossDao();
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
     * Closes the manager instance if it is open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }

    /**
     * Creates, inserts, and returns RoomAmenity object.
     *
     * @param amenity String name of the amenity to be created.
     * @return RoomAmenity created.
     */
    @Override
    public RoomAmenity create(String amenity) {
        RoomAmenity roomAmenity = new RoomAmenity(amenity);
        roomAmenityDao.insert(roomAmenity);
        return roomAmenity;
    }

    /**
     * Creates, inserts, and returns RoomAmenity object.
     *
     * @param amenity RoomAmenity to be created
     * @return RoomAmenity created.
     */
    @Override
    public RoomAmenity create(RoomAmenitiesEnum amenity) {
        return create(amenity.toString());
    }

    /**
     * Gets all the amenities in the given room.
     *
     * @param hotelRoom HotelRoom associated with amenities.
     * @return List<HotelAmenitiesRoomCrossRef> associated with the room.
     */
    @Deprecated // Move to room amenity cross manager
    public List<RoomAmenity> getAmenitiesInRoom(HotelRoom hotelRoom) {
        List<String> ids = roomAmenitiesCrossDao.getWith(hotelRoom.roomId);
        return roomAmenityDao.getIdMatch(ids.toArray(new String[0]));
    }
}
