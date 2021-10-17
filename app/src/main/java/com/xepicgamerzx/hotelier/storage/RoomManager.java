package com.xepicgamerzx.hotelier.storage;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.Room;

import java.util.List;

public class RoomManager {

    public void setRooms(List<Room> rooms, Hotel hotel) {
        for (Room room : rooms) {
            room.setHotel(hotel);
        }
    }

}
