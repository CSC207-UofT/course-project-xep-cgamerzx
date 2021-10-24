package com.xepicgamerzx.hotelier.objects;

import androidx.room.Entity;

@Entity(primaryKeys = {"roomID", "bedID"})
public class BedsRoomCrossRef {
    public int roomID;
    public String bedID;
}
