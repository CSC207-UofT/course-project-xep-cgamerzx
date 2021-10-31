package com.xepicgamerzx.hotelier.storage;

import java.util.List;

public interface StringManager<T> extends EntityManager<T>{
    /**
     * Inserts T objects to their database.
     *
     * @param object T object(s) to be inserted into the database.
     */
    void insert(T... object);

    /**
     * Gets objects with matching primary key IDs.
     *
     * @param ID String ID(s) acting as primary key to be searched for.
     * @return List<T> of objects with primary keys that match ID(s).
     */
    List<T> get(String... ID);
}
