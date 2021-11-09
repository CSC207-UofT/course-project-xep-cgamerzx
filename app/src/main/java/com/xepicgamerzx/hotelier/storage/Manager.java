package com.xepicgamerzx.hotelier.storage;

import java.util.List;

/**
 *
 * @param <T> Entity Type.
 * @param <I> Entity ID type.
 * @param <R> Insert return type.
 */
public interface Manager<T, I, R> {

    /**
     * Gets all instances of <T> in the database.
     *
     * @return List<T> saved in the database.
     */
    List<T> getAll();

    /**
     * Updates <T> object(s) in the database.
     *
     * @param object <T> object(s) to be updated in the database.
     */
    void update(T... object);

    /**
     * Closes the manager instance if it is open.
     */
    void close();

    /**
     * Inserts <T> objects to their database.
     *
     * @param object <T> object(s) to be inserted into the database.
     * @return <R>[] auto-generated IDs of inserted objects.
     */
    R insert(T... object);

    /**
     * Gets objects with matching primary key IDs.
     *
     * @param ID <I> ID(s) acting as primary key to be searched for.
     * @return List<T> of objects with primary keys that match ID(s).
     */
    List<T> get(I... ID);
}
