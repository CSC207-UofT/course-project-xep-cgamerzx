package com.xepicgamerzx.hotelier.objects.hotel_objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity()
public class Bed extends UniqueEntity {
    public Bed (@NonNull String uniqueId){
        super(uniqueId);
    }

    public Bed (BedSizeEnum bedSize){
        super(bedSize.toString());
    }
}
