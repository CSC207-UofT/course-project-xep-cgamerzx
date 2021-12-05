package com.xepicgamerzx.hotelier.objects.OldObjects;

//import java.io.Serializable;
//import java.util.List;
//
//public class HotelOld implements Serializable {
//
//    private String name;
//    private AddressOld address;
//    private List<OldRoom> rooms;
////    private int starClass;
//
//    public HotelOld() {
//        // Default constructor required for calls to DataSnapshot.getValue(User.class)
//    }
//
//    /**
//     * Create a new Hotel
//     *
//     * @param name    the name of this hotel
//     * @param address the address of this hotel
//     * @param rooms   the rooms that are in this hotel
//     *                //* @param amenities the amenities included in this hotel
//     *                //* @param starClass amount of stars this hotel is
//     */
//    public HotelOld(String name, AddressOld address, List<OldRoom> rooms) {
//        this.name = name;
//        this.address = address;
//        this.rooms = rooms;
//        //        this.amenities = amenities;
//        //        this.starClass = starClass;
//    }
//
//    public HotelOld(String name) {
//        this.name = name;
//        //        this.amenities = amenities;
//        //        this.starClass = starClass;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public AddressOld getAddress() {
//        return this.address;
//    }
//
//    public List<OldRoom> getRooms() {
//        return this.rooms;
//    }
//
//    public int getNumberOfRooms() {
//        return this.rooms.size();
//    }
//
//    /**
//     * Checks every room in a hotel, finds the cheapest, and most expensive room and returns the price range.
//     *
//     * @return an array in the format of {minPrice, maxPrice}
//     */
//    public double[] getPrinceRange() {
//        List<OldRoom> rooms = this.rooms;
//
//        double lowestPrice = rooms.get(0).getPrice();
//        double largestPrice = rooms.get(0).getPrice();
//
//
//        for (OldRoom room : rooms) {
//            largestPrice = room.getPrice();
//
//            if (largestPrice < lowestPrice) {
//                lowestPrice = largestPrice;
//                System.out.println("hi");
//            }
//
//        }
//
//        double[] a = new double[2];
//        a[0] = lowestPrice;
//        a[1] = largestPrice;
//
//        return a;
//    }
//
//    /**
//     * toString Method.
//     *
//     * @return a string for a hotel.
//     */
//    @Override
//    public String toString() {
//        String name = String.format("Name: %s", this.name);
//        String hotelAddress = String.format("\nAddress: %s", this.address.getFullStreet());
//        String rooms = String.format("\nRooms: %s", this.rooms);
//
//        return String.format(name + hotelAddress + rooms);
//    }
//
//}

import java.io.Serializable;
import java.util.HashMap;

@Deprecated
public class HotelOld implements Serializable {
    private String name;
    private AddressOld address;
    private HashMap<String, OldRoom> rooms;

    public HotelOld() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public HotelOld(String name, AddressOld address, HashMap<String, OldRoom> rooms) {
        this.name = name;
        this.address = address;
        this.rooms = rooms;
    }

    public HotelOld(String name, AddressOld address) {
        this.name = name;
        this.address = address;
    }

    public HotelOld(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public AddressOld getAddress() {
        return address;
    }

    public Object getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return this.getRooms().toString();
    }
}
