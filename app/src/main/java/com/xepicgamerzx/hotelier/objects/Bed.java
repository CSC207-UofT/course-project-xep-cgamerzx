package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity()
public class Bed extends UniqueEntity {
    public Bed (@NonNull String id){
        super(id);
    }

    public Bed (BedSizeEnum bedSize){
        super(bedSize.toString());
    }
}
