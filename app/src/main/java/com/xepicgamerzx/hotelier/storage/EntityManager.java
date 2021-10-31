package com.xepicgamerzx.hotelier.storage;

import java.util.List;

public interface EntityManager<T> {
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
}
