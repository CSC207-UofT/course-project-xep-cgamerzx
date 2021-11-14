package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.common.base.Objects;

/**
 * Entities where the name is the ID such that there can't be duplicates.
 */
public abstract class UniqueEntity {
    @NonNull
    @PrimaryKey
    private final String id;

    protected UniqueEntity(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniqueEntity that = (UniqueEntity) o;
        return Objects.equal(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
