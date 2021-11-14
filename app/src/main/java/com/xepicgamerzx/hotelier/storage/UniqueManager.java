package com.xepicgamerzx.hotelier.storage;

import com.xepicgamerzx.hotelier.objects.LabeledEnum;
import com.xepicgamerzx.hotelier.objects.UniqueEntity;

public interface UniqueManager<T extends UniqueEntity, E extends LabeledEnum> extends Manager<T, String, Void> {
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
