package com.xepicgamerzx.hotelier.storage.hotel_managers;

import com.xepicgamerzx.hotelier.objects.hotel_objects.LabeledEnum;
import com.xepicgamerzx.hotelier.objects.hotel_objects.UniqueEntity;

public interface UniqueManager<T extends UniqueEntity, E extends LabeledEnum> extends Manager {
    /**
     * Create unique object and insert it into database.
     *
     * @param id String unique ID of object.
     * @return <T> unique object created
     */
    T create(String id);

    /**
     * Create unique object and insert it into database.
     *
     * @param id <E> LabeledEnum of unique object to be created.
     * @return <T> unique object created
     */
    T create(E id);

    /**
     * Create unique object and insert it into database.
     *
     * @param id String unique ID of object.
     * @return String Id of unique object created
     */
    String createId(String id);

    /**
     * Create unique object and insert it into database.
     *
     * @param id <E> LabeledEnum of unique object to be created.
     * @return String Id of unique object created
     */
    String createId(E id);
}