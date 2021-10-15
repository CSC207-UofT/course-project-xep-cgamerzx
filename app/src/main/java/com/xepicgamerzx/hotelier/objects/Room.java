package com.xepicgamerzx.hotelier.objects;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Room {

    private LocalDate[] schedule;
    private int capacity;
    private ArrayList<Bed> beds;
    private Hotel hotel;
    private roomAmenities roomAmenities;


    // Theres no tuples in Java so I did an array with 2 spots corresponding to the first day of
    // availability and last day of availability feel free to change this.
    /**
     * Crates a new Room with given schedule, capacity, beds
     * @param startAvailability the first day where a room is available
     * @param endAvailability the last day of when a room is available
     * @param capacity The maximum number of people that can sleep in this Room
     * @param beds A list of beds in this room
     * @param hotel The hotel that this room is apart of
     * @param amenities The amenities that this room has
     */
    public Room(LocalDate startAvailability, LocalDate endAvailability, int capacity,
                ArrayList<Bed> beds, Hotel hotel, roomAmenities amenities) {
        this.schedule = new LocalDate[2];
        this.schedule[0] = startAvailability;
        this.schedule[1] = endAvailability;
        this.capacity = capacity;
        this.beds = beds;
        this.hotel = hotel;
        this.roomAmenities = amenities;
    }

}
