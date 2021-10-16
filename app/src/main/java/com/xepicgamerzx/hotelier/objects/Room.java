package com.xepicgamerzx.hotelier.objects;

import java.time.LocalDate;
import java.util.ArrayList;

public class Room {

    private long[] schedule;
    private int capacity;
    private ArrayList<Bed> beds;
    private Hotel hotel;
    private RoomAmenities roomAmenities;
    private long price;

    // Theres no tuples in Java so I did an array with 2 spots corresponding to the first day of
    // availability and last day of availability feel free to change this.
    /**
     * Crates a new Room with given schedule, capacity, beds
     * @param startAvailability the first day where a room is available
     * @param endAvailability the last day of when a room is available
     * @param capacity The maximum number of people that can sleep in this Room
     * @param beds A list of beds in this room
    //* @param hotel The hotel that this room is apart of
    //* @param amenities The amenities that this room has
     */
    public Room(long startAvailability, long endAvailability, int capacity,
                ArrayList<Bed> beds, long price) {
        this.schedule = new long[2];
        this.schedule[0] = startAvailability;
        this.schedule[1] = endAvailability;
        this.capacity = capacity;
        this.beds = beds;
        this.price = price;
        // this.roomAmenities = amenities;  * commenting out for now
    }

    public double getPrice() {
        return this.price;
    }

    public long[] getSchedule() {
        return this.schedule;
    }

    public ArrayList<Bed> getBeds() {
        return this.beds;
    }

    public int getCapacity() {
        return this.capacity;
    }

    /**
     * A room has a reference to a hotel. This method sets the hotel that the room is in.
     * @param hotel
     *
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return String.format("Schedule: (%s, %s) \nCapacity: %d \nBeds: %s \nPrice: %d",
                this.getSchedule()[0], this.getSchedule()[1], this.capacity, this.beds, this.price);
    }

}
