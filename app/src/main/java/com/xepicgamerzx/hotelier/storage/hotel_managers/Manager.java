package com.xepicgamerzx.hotelier.storage.hotel_managers;

/**
 * Interface for classes involved in managing entities and their persistence in the database.
 *
 * @param <T> Entity Type.
 * @param <R> Insert return type.
 */
public interface Manager<T, R> {
    /**
     * Closes the manager if already open.
     */
    void close();
}
