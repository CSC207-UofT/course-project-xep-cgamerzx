package com.xepicgamerzx.hotelier.storage;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.RoomAmenitiesCrossRef;
import com.xepicgamerzx.hotelier.objects.RoomAmenity;
import com.xepicgamerzx.hotelier.storage.dao.RoomAmenitiesCrossDao;

import java.util.List;

public class RoomAmenitiesCrossManager implements CrossManager<RoomAmenitiesCrossRef, HotelRoom, RoomAmenity> {
    private static volatile RoomAmenitiesCrossManager INSTANCE;

    private final HotelierDatabase db;
    private final RoomAmenitiesCrossDao roomAmenitiesCrossDao;
    private final RoomManager roomManager;
    private final RoomAmenityManager roomAmenityManager;


    private RoomAmenitiesCrossManager(HotelierDatabase dbInstance) {
        db = dbInstance;
        roomAmenitiesCrossDao = db.roomAmenitiesCrossDao();
        roomAmenityManager = RoomAmenityManager.getManager(db);
        roomManager = RoomManager.getManager(db);
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
     * Create and insert relationship between <N> and <U> into <T> database.
     *
     * @param nonUniqueEntity <N> being assigned to uniqueEntity.
     * @param uniqueEntity    <U> being assigned to nonUniqueEntity.
     * @return <T> crossRef created.
     */
    @Override
    public RoomAmenitiesCrossRef createRelationship(HotelRoom nonUniqueEntity, RoomAmenity uniqueEntity) {
        return null;
    }

    /**
     * Get all <N> associated with uniqueEntity.
     *
     * @param uniqueEntity <U> key to search with.
     * @return List<N> associated with UniqueEntity.
     */
    @Override
    public List<HotelRoom> getRelated(RoomAmenity uniqueEntity) {
        return null;
    }

    /**
     * Get all <U> associated with nonUniqueEntity.
     *
     * @param nonUniqueEntity <N> key to search with.
     * @return List<U> associated with nonUniqueEntity.
     */
    @Override
    public List<RoomAmenity> getRelated(HotelRoom nonUniqueEntity) {
        return null;
    }

    /**
     * Get all <T> cross references associated with uniqueEntity
     *
     * @param uniqueEntity <U> key to search with.
     * @return List<T> cross references associated with <U>
     */
    @Override
    public List<RoomAmenitiesCrossRef> getRelatedCross(RoomAmenity uniqueEntity) {
        return null;
    }

    /**
     * Get all <T> cross references associated with nonUniqueEntity
     *
     * @param nonUniqueEntity <N> key to search with.
     * @return List<T> cross references associated with <N>
     */
    @Override
    public List<RoomAmenitiesCrossRef> getRelatedCross(HotelRoom nonUniqueEntity) {
        return null;
    }

    /**
     * Gets all instances of <T> in the database.
     *
     * @return List<T> saved in the database.
     */
    @Override
    public List<RoomAmenitiesCrossRef> getAll() {
        return null;
    }

    /**
     * Updates <T> object(s) in the database.
     *
     * @param object <T> object(s) to be updated in the database.
     */
    @Override
    public void update(RoomAmenitiesCrossRef... object) {

    }

    /**
     * Closes the manager instance if it is open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }

    /**
     * Inserts <T> objects to their database.
     *
     * @param object <T> object(s) to be inserted into the database.
     * @return <R>[] auto-generated IDs of inserted objects.
     */
    @Override
    public Void insert(RoomAmenitiesCrossRef... object) {
        return null;
    }
}
