package com.xepicgamerzx.hotelier.storage.hotel_reference_managers;

import com.xepicgamerzx.hotelier.storage.hotel_managers.Manager;

import java.util.List;

/**
 *
 * @param <T> Entity Type.
 * @param <I> Entity ID type.
 * @param <R> Insert return type.
 */
public interface DiscreteManager <T, I, R> extends Manager<T, R> {
    /**
     * Gets objects with matching primary key IDs.
     *
     * @param ID <I> ID(s) acting as primary key to be searched for.
     * @return List<T> of objects with primary keys that match ID(s).
     */
    List<T> get(I... ID);
}
