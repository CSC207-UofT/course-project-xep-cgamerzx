package com.xepicgamerzx.hotelier.storage;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelManager {
    private List<Hotel> hotels = new ArrayList<Hotel>();


    public void loadHotels() {

    }


    public void addHotel(Hotel hotel) {
        if (!(hotels.contains(hotel))) {
            hotels.add(hotel);
        }
    }

    public List<Hotel> getAllHotels() {
        return this.hotels;
    }


    public List<Room> getAllHotelsRooms() {
        List<Room> allRooms = new ArrayList<Room>();
        for (Hotel hotel : this.hotels) {
            allRooms.addAll(getOneHotelsRooms(hotel));
        }

        return allRooms;
    }

    /**
     *
     * @param hotel
     * @return return all of the rooms in a specified hotel.
     */
    public List<Room> getOneHotelsRooms(Hotel hotel){
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
