package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

/**
 * Entities where the name is the ID such that there can't be duplicates.
 */
public class UniqueEntity {
    @NonNull
    @PrimaryKey
    private final String id;

    protected UniqueEntity(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UniqueEntity)) return false;

        UniqueEntity that = (UniqueEntity) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
