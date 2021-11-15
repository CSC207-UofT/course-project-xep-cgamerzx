package com.xepicgamerzx.hotelier.storage.hotel_reference_managers;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomAmenitiesCrossRef;
import com.xepicgamerzx.hotelier.objects.hotel_objects.RoomAmenity;
import com.xepicgamerzx.hotelier.storage.dao.RoomAmenitiesCrossDao;
import com.xepicgamerzx.hotelier.storage.hotel_managers.RoomManager;
import com.xepicgamerzx.hotelier.storage.hotelier_database.HotelierDatabase;

import java.util.List;

public class RoomAmenitiesCrossManager implements CrossManager<RoomAmenitiesCrossRef, HotelRoom, RoomAmenity> {
    private static volatile RoomAmenitiesCrossManager INSTANCE;

    private final RoomAmenitiesCrossDao roomAmenitiesCrossDao;
    private final RoomManager roomManager;
    private final RoomAmenityManager roomAmenityManager;


    private RoomAmenitiesCrossManager(HotelierDatabase dbInstance) {
        roomAmenitiesCrossDao = dbInstance.roomAmenitiesCrossDao();
        roomAmenityManager = RoomAmenityManager.getManager(dbInstance);
        roomManager = RoomManager.getManager(dbInstance);
    }

    public static RoomAmenitiesCrossManager getManager(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new RoomAmenitiesCrossManager(HotelierDatabase.getDatabase(application));
        }
        return INSTANCE;
    }

    public static RoomAmenitiesCrossManager getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) {
            INSTANCE = new RoomAmenitiesCrossManager(dbInstance);
        }
        return INSTANCE;
    }


    /**
     * Create and insert relationship between hotelRoom and roomAmenity into RoomAmenitiesCrossRef database.
     *
     * @param hotelRoom HotelRoom being assigned to roomAmenity.
     * @param roomAmenity    RoomAmenity being assigned to HotelRoom.
     * @return RoomAmenitiesCrossRef created.
     */
    @Override
    public RoomAmenitiesCrossRef createRelationship(HotelRoom hotelRoom, RoomAmenity roomAmenity) {
        RoomAmenitiesCrossRef crossRef = new RoomAmenitiesCrossRef(hotelRoom, roomAmenity);
        insert(crossRef);
        return crossRef;
    }

    /**
     * Get all HotelRoom associated with RoomAmenity.
     *
     * @param roomAmenity RoomAmenity key to search with.
     * @return List<HotelRoom> associated with RoomAmenity.
     */
    @Override
    public List<HotelRoom> getRelated(RoomAmenity roomAmenity) {
        List<Long> ids = roomAmenitiesCrossDao.getWith(roomAmenity.getUniqueId());
        return roomManager.get(ids.toArray(new Long[0]));
    }

    /**
     * Get all RoomAmenity associated with HotelRoom.
     *
     * @param hotelRoom HotelRoom key to search with.
     * @return List<RoomAmenity> associated with HotelRoom.
     */
    @Override
    public List<RoomAmenity> getRelated(HotelRoom hotelRoom) {
        List<String> ids = roomAmenitiesCrossDao.getWith(hotelRoom.roomID);
        return roomAmenityManager.get(ids.toArray(new String[0]));
    }

    /**
     * Get all RoomAmenitiesCrossRef cross references associated with RoomAmenity
     *
     * @param roomAmenity RoomAmenity key to search with.
     * @return List<RoomAmenitiesCrossRef> cross references associated with RoomAmenity
     */
    @Override
    public List<RoomAmenitiesCrossRef> getRelatedCross(RoomAmenity roomAmenity) {
        return roomAmenitiesCrossDao.getCrossWith(roomAmenity.getUniqueId());
    }

    /**
     * Get all RoomAmenitiesCrossRef cross references associated with HotelRoom
     *
     * @param hotelRoom HotelRoom key to search with.
     * @return List<RoomAmenitiesCrossRef> cross references associated with HotelRoom.
     */
    @Override
    public List<RoomAmenitiesCrossRef> getRelatedCross(HotelRoom hotelRoom) {
        return roomAmenitiesCrossDao.getCrossWith(hotelRoom.roomID);
    }

    /**
     * Gets all instances of RoomAmenitiesCrossRef in the database.
     *
     * @return List<RoomAmenitiesCrossRef> saved in the database.
     */
    @Override
    public List<RoomAmenitiesCrossRef> getAll() {
        return roomAmenitiesCrossDao.getAll();
    }

    /**
     * Updates RoomAmenitiesCrossRef roomAmenitiesCrossRef(s) in the database.
     *
     * @param roomAmenitiesCrossRef <RoomAmenitiesCrossRef> roomAmenitiesCrossRef(s) to be updated in the database.
     */
    @Override
    public void update(RoomAmenitiesCrossRef... roomAmenitiesCrossRef) {
        roomAmenitiesCrossDao.update(roomAmenitiesCrossRef);
    }

    /**
     * Closes the manager instance if it is open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }

    /**
     * Inserts RoomAmenitiesCrossRef objects to their database.
     *
     * @param roomAmenitiesCrossRef RoomAmenitiesCrossRef(s) to be inserted into the database.
     * @return null.
     */
    @Override
    public Void insert(RoomAmenitiesCrossRef... roomAmenitiesCrossRef) {
        return roomAmenitiesCrossDao.insert(roomAmenitiesCrossRef);
    }

    /**
     * Adds RoomAmenity object to HotelRoom.
     *
     * @param hotelRoom HotelRoom to which we are adding RoomAmenity.
     * @param roomAmenity RoomAmenity that we are adding to HotelRoom.
     */
    public void addAmenityToRoom(HotelRoom hotelRoom, RoomAmenity roomAmenity) {
        RoomAmenitiesCrossRef roomAmenitiesCrossRef = new RoomAmenitiesCrossRef(hotelRoom, roomAmenity);
        roomAmenitiesCrossDao.insert(roomAmenitiesCrossRef);
    }
}
