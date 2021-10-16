package com.xepicgamerzx.hotelier.objects;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String name;
    private Address address;
    private List<Room> rooms;
    private HotelAmenities amenities;
    private int starClass;

    /**
     * Create a new Hotel
     * @param name the name of this hotel
     * @param address the address of this hotel
     * @param rooms the rooms that are in this hotel
     //* @param amenities the amenities included in this hotel
     //* @param starClass amount of stars this hotel is
     */
    public Hotel(String name, Address address, List<Room> rooms) {
        this.name = name;
        this.address = address;
        this.rooms = rooms;
//        this.amenities = amenities;
//        this.starClass = starClass;
    }

    public String getName() {
        return this.name;
    }

    public Address getAddress() {
        return this.address;
    }

    public List<Room> getRooms() {
        return this.rooms;
    }

    /**
     * Checks every room in a hotel, finds the cheapest, and most expensive room and returns the price range.
     * @return an array in the format of {minPrice, maxPrice}
     */
    public double[] getPrinceRange() {
        List<Room> rooms = this.rooms;

        double lowestPrice = rooms.get(0).getPrice();
        double largestPrice = rooms.get(0).getPrice();


        for (Room room : rooms) {
            largestPrice = room.getPrice();

            if (largestPrice < lowestPrice) {
                lowestPrice = largestPrice;
                System.out.println("hi");
            }

        }

        double[] a = new double[2];
        a[0] = lowestPrice;
        a[1] = largestPrice;

        return a;
    }

    /**
     *
     * @return a string for a hotel.
     */
    @Override
    public String toString() {
        String name = String.format("Name: %s", this.name);
        String hotelAddress = String.format("\nAddress: %s", this.address.getFullStreet());
        String rooms = String.format("\nRooms: %s", this.rooms);

        return String.format(name + hotelAddress + rooms);
    }


}
