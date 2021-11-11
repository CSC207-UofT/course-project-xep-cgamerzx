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
}
