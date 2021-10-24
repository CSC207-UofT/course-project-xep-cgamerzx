package com.xepicgamerzx.hotelier.customer;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;

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
     * @param hotels List of Hotels to be sorted
     * @return List of Hotels to be returned.
     */
    public List<Hotel> sortHotelsByPrice(List<Hotel> hotels) {
        HashMap<Hotel, Double> hotelsToMinPrice = new HashMap<>();

        ArrayList<Hotel> less = new ArrayList<>();
        ArrayList<Hotel> equal = new ArrayList<>();
        ArrayList<Hotel> more = new ArrayList<>();

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
     * Returns a string describing the hotelRooms that match the requirements of the customer request.
     *
     * @return string describing hotelRooms that match the requirements of the customer request.
     * @throws NoSuchMethodException Method is not yet implemented
     */
    public String returnStringRooms() throws NoSuchMethodException {
        throw new NoSuchMethodException();
        //TODO just use the to string methods and concat them with added lines between them
    }

    /*** Returns an array list of hotelRooms that match the requirements of the customer request.
     *
     * @return array list of hotelRooms that match the requirements of the customer request.
     * @throws NoSuchMethodException Method is not yet implemented
     */
    public ArrayList<HotelRoom> getValidRooms() throws NoSuchMethodException {


        throw new NoSuchMethodException();
    }

    /**
     * Returns an array list of hotelRooms that match the requirements of the customer request.
     *
     * @param hotel hotel to search for valid hotelRooms from.
     * @return Array list of hotelRooms that match the request
     * @throws NoSuchMethodException Method is not yet implemented
     */
    public ArrayList<HotelRoom> getValidRooms(Hotel hotel) throws NoSuchMethodException {


        throw new NoSuchMethodException();
    }

    /**
     * Checks whenever or not the hotelRoom matches the requirements of the customer request.
     *
     * @return true if the hotelRoom matches the requirements, false otherwise.
     */
    public Boolean validateCustomerRooms(HotelRoom hotelRoom) {

        return false;
    }

    /**
     * Returns an array list of valid hotels given the customer's request.
     *
     * @return array list of valid hotels.
     * @throws NoSuchMethodException Method is not yet implemented
     */
    public ArrayList<Hotel> getValidHotels() throws NoSuchMethodException {

        throw new NoSuchMethodException();
    }
}
