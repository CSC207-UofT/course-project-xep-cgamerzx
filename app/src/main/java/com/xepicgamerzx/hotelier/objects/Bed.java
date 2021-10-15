package com.xepicgamerzx.hotelier.objects;

public class Bed {

    private String size;
    private Room room;

    /**
     * Creates a new Bed
     * @param size a string representing the size of the bed
     * TODO: Precondition on size where you can only have valid strings (King, Queen, ...)
     */
    public Bed(String size) {
        this.size = size;
    }

    /**
     * Gives a Bed a reference to the Room it is in.
     * @param room A room object
     */
    public void setRoom(Room room) {
        this.room = room;
    }
}
