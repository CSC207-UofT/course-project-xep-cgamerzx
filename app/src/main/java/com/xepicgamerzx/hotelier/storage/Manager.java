package com.xepicgamerzx.hotelier.storage;

import java.util.List;

/**
 * Interface for classes involved in managing entities and their persistence in the database.
 *
 * @param <T> Entity Type.
 * @param <R> Insert return type.
 */
public interface Manager<T, R> {
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
}
