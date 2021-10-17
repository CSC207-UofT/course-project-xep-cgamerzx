package com.xepicgamerzx.hotelier.storage;

import android.content.Context;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class to manage all the hotels in our database.
 */
public class HotelManager implements Serializable {
    private List<Hotel> hotels = new ArrayList<>();
    FileReadWrite<List<Hotel>> frw = new FileReadWrite<>();

    /**
     * A method to load hotels when opening the app and set the hotels instance variable to them
     * @param appContext Context of the app, use View.getContext()
     * @return return the loaded hotels.
     */
    public List<Hotel> loadHotels(Context appContext) {
        List<Hotel> list = frw.readData("file.dat", appContext);

        if(list != null) {
            this.hotels = list;
            return this.hotels;
        }

        return this.hotels;
    }

    /**
     * A method to save hotels to a file, which is saved to the database.
     *
     * @param appContext Context of app.
     */
    public void saveData(Context appContext) {
        frw.writeData(hotels, "file.dat", appContext);
    }

    /**
     * Append a hotel object to the hotels ArrayList.
     * @param hotel
     *
     */
    public void addHotel(Hotel hotel) {
        if (!(hotels.contains(hotel))) {
            this.hotels.add(hotel);
        }
    }

    //    public void deleteHotel(Hotel hotel) {
    //        if(hotels.contains(hotel)) {
    //            this.hotels.remove(hotel);
    //        }
    //    }

    public List<Hotel> getAllHotels() {
        return this.hotels;
    }

    /**
     * Returns a string of a hotels priceRange.
     * @param hotel
     * @return
     */
    public String hotelPriceRangeString(Hotel hotel) {
        double[] priceRange = hotel.getPrinceRange();
        String msg;
        if (priceRange[0] == priceRange[1]) {
            msg = String.format("Price: %s", Double.toString(priceRange[0]));
        } else {
            msg = String.format("Price Range: %s - %s", Double.toString(priceRange[0]), Double.toString(priceRange[1]));
        }
        return msg;
    }

    /**
     * Returns every room in the database (every hotels rooms).
     * @return
     */
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
     * @return return all of the rooms in ONE specified hotel.
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
