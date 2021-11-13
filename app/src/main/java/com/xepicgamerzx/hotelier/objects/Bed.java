package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity()
public class Bed implements Serializable {
    @NonNull
    @PrimaryKey()
    private final String bedID;

    public Bed (String bedID){
        this.bedID = bedID;
    }

    public Bed (BedSizeEnum bedID){
        this.bedID = bedID.toString();
    }

    public String getBedID() {
        return bedID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bed)) return false;

        Bed bed = (Bed) o;

        return getBedID().equals(bed.getBedID());
    }

    @Override
    public int hashCode() {
        return getBedID().hashCode();
    }
}
