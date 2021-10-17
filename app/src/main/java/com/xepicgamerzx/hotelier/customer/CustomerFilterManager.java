package com.xepicgamerzx.hotelier.customer;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerFilterManager {


    /**
     * Returns the hotels sorted by minimum price.
     *
     * @param hotels
     * @return
     */
    public List<Hotel> sortHotelsByPrice(List<Hotel> hotels) {
        HashMap<Hotel, Double> hotelsToMinPrice = new HashMap<Hotel, Double>();

        ArrayList<Hotel> less = new ArrayList<Hotel>();
        ArrayList<Hotel> equal = new ArrayList<Hotel>();
        ArrayList<Hotel> more = new ArrayList<Hotel>();

        if (hotels.size() > 1) {
            Hotel pivot_obj = hotels.get(0);

            // Might just add a maxPrice and lowestPrice attribute to a hotel instead of going getPriceRange.
            for (Hotel hotel : hotels) {
                if (hotel.getPrinceRange()[0] < pivot_obj.getPrinceRange()[0]) {
                    less.add(hotel);
                } else if (hotel.getPrinceRange()[0] == pivot_obj.getPrinceRange()[0]) {
                    equal.add(hotel);
                } else if (hotel.getPrinceRange()[0] > pivot_obj.getPrinceRange()[0]) {
                    more.add(hotel);
                }

            }

            List<Hotel> newList = Stream.of(sortHotelsByPrice(less), equal, sortHotelsByPrice(more))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

            return newList;
        } else {
            return hotels;
        }
    }

    /**
     * Returns a string describing the rooms that match the requirements of the customer request.
     *
     * @return string describing rooms that match the requirements of the customer request.
     * @throws NoSuchMethodException
     */
    public String returnStringRooms() throws NoSuchMethodException {
        throw new NoSuchMethodException();
        //TODO just use the to string methods and concat them with added lines between them
    }

    /*** Returns an array list of rooms that match the requirements of the customer request.
     *
     * @return array list of rooms that match the requirements of the customer request.
     * @throws NoSuchMethodException
     */
    public ArrayList<Room> getValidRooms() throws NoSuchMethodException {


        throw new NoSuchMethodException();
    }

    /**
     * Returns an array list of rooms that match the requirements of the customer request.
     *
     * @param hotel hotel to search for valid rooms from.
     * @return
     * @throws NoSuchMethodException
     */
    public ArrayList<Room> getValidRooms(Hotel hotel) throws NoSuchMethodException {


        throw new NoSuchMethodException();
    }

    /**
     * Checks whenever or not the room matches the requirements of the customer request.
     *
     * @return true if the room matches the requirements, false otherwise.
     */
    public Boolean validateCustomerRooms(Room room) {

        return false;
    }

    /**
     * Returns an array list of valid hotels given the customer's request.
     *
     * @return array list of valid hotels.
     */
    public ArrayList<Hotel> getValidHotels() throws NoSuchMethodException {

        throw new NoSuchMethodException();
    }
}
