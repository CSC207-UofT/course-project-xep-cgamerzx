package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public abstract class CrossRef{
    @NonNull
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
