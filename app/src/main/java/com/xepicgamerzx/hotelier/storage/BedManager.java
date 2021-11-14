package com.xepicgamerzx.hotelier.storage;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.BedSizeEnum;
import com.xepicgamerzx.hotelier.objects.BedsRoomCrossRef;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.dao.BedDao;
import com.xepicgamerzx.hotelier.storage.dao.BedRoomCrossDao;

import java.util.HashMap;
import java.util.List;

/**
 * A class to manage all the Beds in the database.
 */
public class BedManager implements Manager<Bed, String, Void> {
    private static volatile BedManager INSTANCE;

    private final HotelierDatabase db;
    private final BedDao bedDao;
    private final BedRoomCrossDao bedRoomCrossDao;

    private BedManager(Application application) {
        db = HotelierDatabase.getDatabase(application);
        bedDao = db.bedDao();
        bedRoomCrossDao = db.bedRoomCrossDao();
    }

    private BedManager(HotelierDatabase dbInstance) {
        db = dbInstance;
        bedDao = db.bedDao();
        bedRoomCrossDao = db.bedRoomCrossDao();
    }

    public static BedManager getManager(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new BedManager(application);
        }
        return INSTANCE;
    }

    public static BedManager getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) {
            INSTANCE = new BedManager(dbInstance);
        }
        return INSTANCE;
    }

    public Bed create(String bedSize) {
        Bed bed = new Bed(bedSize);
        insert(bed);
        return bed;
    }

    public Bed create(BedSizeEnum bedSizeEnum) {
        Bed bed = new Bed(bedSizeEnum.toString());
        insert(bed);
        return bed;
    }

    /**
     * Inserts the beds(s) to the Hotel database.
     *
     * @param bed Bed object(s) to be saved.
     * @return null
     */
    @Override
    public Void insert(Bed... bed) {
        bedDao.insert(bed);
        return null;
    }

    /**
     * Create and insert relationship between Bed and HotelRoom into BedRoomCrossRef database.
     *
     * @param bed Bed being assigned to a hotel room.
     * @param hotelRoom HotelRoom being assigned beds.
     * @param bedCount Int number of Bed type associated with the room.
     */
    public void addBedToRoom(Bed bed, HotelRoom hotelRoom, int bedCount) {
        BedsRoomCrossRef bedsRoomCrossRef = new BedsRoomCrossRef(hotelRoom, bed, bedCount);
        bedRoomCrossDao.insert(bedsRoomCrossRef);
    }

    /**
     * Updates Bed object(s) in the database.
     *
     * @param bed Bed object(s) to be updated in the database.
     */
    @Override
    public void update(Bed... bed) {
        bedDao.update(bed);
    }

    /**
     * Get beds with matching bed IDs.
     *
     * @param bedID String bedIDs to be used as search keys.
     * @return List of Beds with matching bedIDs.
     */
    @Override
    public List<Bed> get(String... bedID) {
        return bedDao.getBeds(bedID);
    }


    /**
     * Gets all beds in the Bed database.
     *
     * @return List of the Beds in the Bed database.
     */
    @Override
    public List<Bed> getAll() {
        return bedDao.getAllBeds();
    }

    /**
     * Gets all the beds in the given room.
     *
     * @param hotelRoom HotelRoom associated with beds.
     * @return List<BedsRoomCrossRef> associated with the beds.
     */
    public List<Bed> getBedsInRoom(HotelRoom hotelRoom){
        List<String> ids = bedRoomCrossDao.getBedsInRoom(hotelRoom.roomID);
        return get(ids.toArray(new String[0]));
    }

    /**
     * Gets all the beds in the given room as well as the number of each type of bed in the room.
     *
     * @param hotelRoom HotelRoom associated with beds.
     * @return HashMap<Bed, Integer> of beds in the room and the associated count.
     */
    public HashMap<Bed, Integer> getBedsInRoomCount(HotelRoom hotelRoom){
        List<BedsRoomCrossRef> ids = bedRoomCrossDao.getBedsCrossInRoom(hotelRoom.roomID);
        HashMap<Bed, Integer> bedCount = new HashMap<>();
        for (BedsRoomCrossRef crossRef : ids){
            bedCount.put(get(crossRef.uniqueId).get(0), crossRef.getBedCount());
        }
        return bedCount;
    }

    /**
     * Closes the manager if already open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }
}
