package com.xepicgamerzx.hotelier.storage;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.Room;

import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    private List<Hotel> hotels;

    public HotelManager(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<Hotel> getAllHotels() {
        return this.hotels;
    }


    /**
     *
     * @param hotel
     * @return return all of the rooms in a specified hotel.
     */
    public List<Room> getAllRooms(Hotel hotel){
        List<Room> rooms = new ArrayList<Room>();

        try {
            for (Hotel hotel_other : this.hotels) {
                if (hotel == hotel_other) {
                    rooms = hotel.getRooms();
                    return rooms;
                }
            }
        } catch(Exception e) {
            System.out.println("Hotel was not found");
        }

        return rooms;
    }


}
