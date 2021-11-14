package com.xepicgamerzx.hotelier.storage;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelAmenitiesCrossRef;
import com.xepicgamerzx.hotelier.objects.HotelAmenity;

import java.util.List;

public class HotelAmenitiesCrossManager implements CrossManager<HotelAmenitiesCrossRef, Hotel, HotelAmenity> {

    /**
     * Create and insert relationship between <N> and <U> into <T> database.
     *
     * @param nonUniqueEntity <N> being assigned to uniqueEntity.
     * @param uniqueEntity    <U> being assigned to nonUniqueEntity.
     * @return <T> crossRef created.
     */
    @Override
    public HotelAmenitiesCrossRef createRelationship(Hotel nonUniqueEntity, HotelAmenity uniqueEntity) {
        return null;
    }

    /**
     * Get all <N> associated with uniqueEntity.
     *
     * @param uniqueEntity <U> key to search with.
     * @return List<N> associated with UniqueEntity.
     */
    @Override
    public List<Hotel> getRelated(HotelAmenity uniqueEntity) {
        return null;
    }

    /**
     * Get all <U> associated with nonUniqueEntity.
     *
     * @param nonUniqueEntity <N> key to search with.
     * @return List<U> associated with nonUniqueEntity.
     */
    @Override
    public List<HotelAmenity> getRelated(Hotel nonUniqueEntity) {
        return null;
    }

    /**
     * Get all <T> cross references associated with uniqueEntity
     *
     * @param uniqueEntity <U> key to search with.
     * @return List<T> cross references associated with <U>
     */
    @Override
    public List<HotelAmenitiesCrossRef> getRelatedCross(HotelAmenity uniqueEntity) {
        return null;
    }

    /**
     * Get all <T> cross references associated with nonUniqueEntity
     *
     * @param nonUniqueEntity <N> key to search with.
     * @return List<T> cross references associated with <N>
     */
    @Override
    public List<HotelAmenitiesCrossRef> getRelatedCross(Hotel nonUniqueEntity) {
        return null;
    }

    /**
     * Gets all instances of <T> in the database.
     *
     * @return List<T> saved in the database.
     */
    @Override
    public List<HotelAmenitiesCrossRef> getAll() {
        return null;
    }

    /**
     * Updates <T> object(s) in the database.
     *
     * @param object <T> object(s) to be updated in the database.
     */
    @Override
    public void update(HotelAmenitiesCrossRef... object) {

    }

    /**
     * Closes the manager instance if it is open.
     */
    @Override
    public void close() {

    }

    /**
     * Inserts <T> objects to their database.
     *
     * @param object <T> object(s) to be inserted into the database.
     * @return null.
     */
    @Override
    public Void insert(HotelAmenitiesCrossRef... object) {
        return null;
    }
}
