package com.xepicgamerzx.hotelier.customer;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.Room;
import com.xepicgamerzx.hotelier.storage.HotelManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomerFilterlManager {

    /** Returns a string describing the rooms that match the requirements of the customer request.
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
    public ArrayList<Room> getValidRooms() throws  NoSuchMethodException{


        throw new NoSuchMethodException();
    }

    /** Returns an array list of rooms that match the requirements of the customer request.
     *
     * @param hotel hotel to search for valid rooms from.
     * @return
     * @throws NoSuchMethodException
     */
    public ArrayList<Room> getValidRooms(Hotel hotel) throws  NoSuchMethodException{


        throw new NoSuchMethodException();
    }

    /** Checks whenever or not the room matches the requirements of the customer request.
     *
     * @return true if the room matches the requirements, false otherwise.
     */
    public Boolean validateCustomerRooms(Room room){

        return false;
    }

    /** Returns an array list of valid hotels given the customer's request.
     *
     * @return array list of valid hotels.
     */
    public ArrayList<Hotel> getValidHotels() throws  NoSuchMethodException{

        throw new NoSuchMethodException();
    }
}
