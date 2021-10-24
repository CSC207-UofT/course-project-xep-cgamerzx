package com.xepicgamerzx.hotelier.objects;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;


@Entity()
public class Hotel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long hotelID;

    private String name;

    @Embedded
    private Address address;

    private int starClass;

    /**
     * Create a new Hotel
     *
     * @param name    the name of this hotel
     * @param address the address of this hotel
     *                //* @param amenities the amenities included in this hotel
     *                //* @param starClass amount of stars this hotel is
     */
    public Hotel(String name, Address address) {
        this.name = name;
        this.address = address;
        //        this.amenities = amenities;
        //        this.starClass = starClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getStarClass() {
        return starClass;
    }

    public void setStarClass(int starClass) {
        this.starClass = starClass;
    }

    /**
     * Checks every hotelRoom in a hotel, finds the cheapest, and most expensive hotelRoom and returns the price range.
     *
     * @return an array in the format of {minPrice, maxPrice}
     */
/*    public double[] getPrinceRange() {
        List<HotelRoom> hotelRooms = this.hotelRooms;

        double lowestPrice = hotelRooms.get(0).getPrice();
        double largestPrice = hotelRooms.get(0).getPrice();


        for (HotelRoom hotelRoom : hotelRooms) {
            largestPrice = hotelRoom.getPrice();

            if (largestPrice < lowestPrice) {
                lowestPrice = largestPrice;
                System.out.println("hi");
            }

        }

        double[] a = new double[2];
        a[0] = lowestPrice;
        a[1] = largestPrice;

        return a;
    }*/

    /**
     * toString Method.
     *
     * @return a string for a hotel.
     */
    @Override
    public String toString() {
        String name = String.format("Name: %s", this.name);
        String hotelAddress = String.format("\nAddress: %s", this.address.getFullStreet());

        return String.format(name + hotelAddress);
    }


}
