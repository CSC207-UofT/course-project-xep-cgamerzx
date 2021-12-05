package com.xepicgamerzx.hotelier.objects.OldObjects;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class OldRoom implements Serializable {

    private long startAvailability;
    private long endAvailability;
    private int capacity;
    private List<BedOld> beds;
    private HotelOld hotel;
    private long price;

    // Theres no tuples in Java so I did an array with 2 spots corresponding to the first day of
    // availability and last day of availability feel free to change this.

    public OldRoom() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    /**
     * Crates a new Room with given schedule, capacity, beds
     *
     * @param startAvailability the first day where a room is available
     * @param endAvailability   the last day of when a room is available
     * @param capacity          The maximum number of people that can sleep in this Room
     * @param beds              A list of beds in this room
     *                          //* @param hotel The hotel that this room is apart of
     *                          //* @param amenities The amenities that this room has
     */
    public OldRoom(long startAvailability, long endAvailability, int capacity,
                   List<BedOld> beds, long price) {
        this.startAvailability = startAvailability;
        this.endAvailability = endAvailability;
        this.capacity = capacity;
        this.beds = beds;
        this.price = price;
        // this.roomAmenities = amenities;  * commenting out for now
    }

    public OldRoom(long startAvailability, long endAvailability, int capacity, long price) {
        this.startAvailability = startAvailability;
        this.endAvailability = endAvailability;
        this.capacity = capacity;
        this.price = price;
        // this.roomAmenities = amenities;  * commenting out for now
    }

    public double getPrice() {
        return this.price;
    }

    public long getStartAvailability() {
        return this.startAvailability;
    }

    public long getEndAvailability() {
        return this.endAvailability;
    }

//    public long[] getSchedule() {
//        return this.schedule;
//    }

    public List<BedOld> getBeds() {
        return this.beds;
    }

    public int getCapacity() {
        return this.capacity;
    }

    /**
     * A room has a reference to a hotel. This method sets the hotel that the room is in.
     *
     * @param hotel Hotel object the room belongs to.
     */
    public void setHotel(HotelOld hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return String.format(Locale.CANADA, "Schedule: (%s, %s) \nCapacity: %d \nBeds: %s \nPrice: %d",
                startAvailability, endAvailability, this.capacity, this.beds, this.price);
    }

}
