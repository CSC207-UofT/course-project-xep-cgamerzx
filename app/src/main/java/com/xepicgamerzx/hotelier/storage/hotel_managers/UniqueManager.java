package com.xepicgamerzx.hotelier.storage.hotel_managers;

import com.xepicgamerzx.hotelier.objects.hotel_objects.LabeledEnum;
import com.xepicgamerzx.hotelier.objects.hotel_objects.UniqueEntity;
import com.xepicgamerzx.hotelier.storage.hotel_reference_managers.DiscreteManager;

public interface UniqueManager<T extends UniqueEntity, E extends LabeledEnum> extends DiscreteManager<T, String, Void> {
    /**
     *
     * @param id
     * @return
     */
    T create(String id);

    /**
     *
     * @param id
     * @return
     */
    T create(E id);
}
