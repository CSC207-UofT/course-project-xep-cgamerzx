package com.xepicgamerzx.hotelier.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity()
public class Bed implements Serializable {
    @PrimaryKey()
    private final String bedID;

    public Bed (String bedID){
        this.bedID = bedID;
    }

    public Bed (BedSize bedID){
        this.bedID = bedID.toString();
    }
}
