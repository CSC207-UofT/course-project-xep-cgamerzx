package com.xepicgamerzx.hotelier.storage.hotel_reference_managers;

import android.app.Application;

import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomBedsCrossRef;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.BedDao;
import com.xepicgamerzx.hotelier.storage.dao.RoomBedsCrossDao;
import com.xepicgamerzx.hotelier.storage.dao.RoomDao;

import java.util.HashMap;
import java.util.List;

public class RoomBedsCrossManager implements CrossManager<RoomBedsCrossRef, HotelRoom, Bed> {
    private static volatile RoomBedsCrossManager INSTANCE;

    private final RoomBedsCrossDao roomBedsCrossDao;
    private final RoomDao roomDao;
    private final BedDao bedDao;

    private RoomBedsCrossManager(HotelierDatabase dbInstance) {
        roomBedsCrossDao = dbInstance.bedRoomCrossDao();
        roomDao = dbInstance.roomDao();
        bedDao = dbInstance.bedDao();
    }

    public static RoomBedsCrossManager getManager(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new RoomBedsCrossManager(HotelierDatabase.getDatabase(application));
        }
        return INSTANCE;
    }

    public static RoomBedsCrossManager getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) {
            INSTANCE = new RoomBedsCrossManager(dbInstance);
        }
        return INSTANCE;
    }

    /**
     * Create and insert relationship between HotelRoom and Bed into RoomBedsCrossRef database.
     *
     * @param hotelRoom HotelRoom being assigned to uniqueEntity.
     * @param bed       Bed being assigned to nonUniqueEntity.
     * @return RoomBedsCrossRef created.
     */
    @Override
    public RoomBedsCrossRef createRelationship(HotelRoom hotelRoom, Bed bed) {
        return createRelationship(hotelRoom, bed, 0);
    }

    /**
     * Create and insert relationship between HotelRoom and Bed into RoomBedsCrossRef database.
     *
     * @param hotelRoom HotelRoom being assigned to uniqueEntity.
     * @param bed       Bed being assigned to nonUniqueEntity.
     * @param bedCount  int number of beds associated with hotelRoom.
     * @return RoomBedsCrossRef created.
     */
    public RoomBedsCrossRef createRelationship(HotelRoom hotelRoom, Bed bed, int bedCount) {
        RoomBedsCrossRef crossRef = new RoomBedsCrossRef(hotelRoom, bed, bedCount);
        roomBedsCrossDao.insert(crossRef);
        return crossRef;
    }

    /**
     * Create and insert relationship between HotelRoom and Bed into RoomBedsCrossRef database.
     *
     * @param hotelRoom long hotel room Id being assigned to uniqueEntity.
     * @param bed       String bed id being assigned to nonUniqueEntity.
     * @param bedCount  int number of beds associated with hotelRoom.
     * @return RoomBedsCrossRef created.
     */
    public RoomBedsCrossRef createRelationship(long hotelRoom, String bed, int bedCount) {
        RoomBedsCrossRef crossRef = new RoomBedsCrossRef(hotelRoom, bed, bedCount);
        roomBedsCrossDao.insert(crossRef);
        return crossRef;
    }

    /**
     * Get all HotelRoom associated with Bed.
     *
     * @param bed Bed key to search with.
     * @return ListHotelRoom associated with UniqueEntity.
     */
    @Override
    public List<HotelRoom> getRelated(Bed bed) {
        List<Long> ids = roomBedsCrossDao.getWith(bed.getUniqueId());
        return roomDao.getIdMatch(ids.toArray(new Long[0]));
    }

    /**
     * Get all HotelRoom associated with at least count number of Beds.
     *
     * @param bed   Bed key to search with.
     * @param count int minimum number of beds to be associated with the HotelRoom.
     * @return ListHotelRoom associated with UniqueEntity.
     */
    public List<HotelRoom> getRelated(Bed bed, int count) {
        List<Long> ids = roomBedsCrossDao.getWith(bed.getUniqueId(), count);
        return roomDao.getIdMatch(ids.toArray(new Long[0]));
    }

    /**
     * Get all Bed associated with HotelRoom.
     *
     * @param hotelRoom HotelRoom key to search with.
     * @return ListBed associated with nonUniqueEntity.
     */
    @Override
    public List<Bed> getRelated(HotelRoom hotelRoom) {
        List<String> ids = roomBedsCrossDao.getWith(hotelRoom.roomId);
        return bedDao.getIdMatch(ids.toArray(new String[0]));
    }

    /**
     * Get all RoomBedsCrossRef cross references associated with Bed
     *
     * @param bed Bed key to search with.
     * @return ListRoomBedsCrossRef cross references associated with Bed
     */
    @Override
    public List<RoomBedsCrossRef> getRelatedCross(Bed bed) {
        return roomBedsCrossDao.getCrossWith(bed.getUniqueId());
    }

    /**
     * Get all RoomBedsCrossRef cross references associated with HotelRoom
     *
     * @param hotelRoom HotelRoom key to search with.
     * @return ListRoomBedsCrossRef cross references associated with HotelRoom
     */
    @Override
    public List<RoomBedsCrossRef> getRelatedCross(HotelRoom hotelRoom) {
        return roomBedsCrossDao.getCrossWith(hotelRoom.roomId);
    }

    /**
     * Closes the manager instance if it is open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }

    /**
     * Gets all the beds in the given room as well as the number of each type of bed in the room.
     *
     * @param hotelRoom HotelRoom associated with beds.
     * @return HashMap<Bed, Integer> of beds in the room and the associated count.
     */
    public HashMap<Bed, Integer> getBedsInRoomCount(HotelRoom hotelRoom) {
        List<RoomBedsCrossRef> ids = getRelatedCross(hotelRoom);
        HashMap<Bed, Integer> bedCount = new HashMap<>();
        for (RoomBedsCrossRef crossRef : ids) {
            bedCount.put(bedDao.getIdMatch(crossRef.uniqueId).get(0), crossRef.getBedCount());
        }
        return bedCount;
    }
}
