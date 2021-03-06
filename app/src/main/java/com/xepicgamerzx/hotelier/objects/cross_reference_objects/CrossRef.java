package com.xepicgamerzx.hotelier.objects.cross_reference_objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

/**
 * Base cross ref object
 */
public abstract class CrossRef {
    @NonNull // required annotation
    @ColumnInfo(index = true)
    public String uniqueId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CrossRef)) return false;

        CrossRef crossRef = (CrossRef) o;

        return uniqueId.equals(crossRef.uniqueId);
    }

    @Override
    public int hashCode() {
        return uniqueId.hashCode();
    }
}
