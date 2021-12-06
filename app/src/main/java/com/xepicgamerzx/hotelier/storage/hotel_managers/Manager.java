package com.xepicgamerzx.hotelier.storage.hotel_managers;

/**
 * Interface for classes involved in managing entities and their persistence in the database.
 *
 */
public interface Manager {
    /**
     * Closes the manager if already open.
     */
    void close();
}
