package com.xepicgamerzx.hotelier.objects;

import java.io.Serializable;

public class Bed implements Serializable {

    private String size;
    public Room room;

    /**
     * Creates a new Bed
     * @param size a string representing the size of the bed
     * TODO: Precondition on size where you can only have valid strings (King, Queen, ...)
     */
    public Bed(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    /**
     * Gives a Bed a reference to the Room it is in.
     * @param room A room object
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        if (this.room != null) {
            return this.room;
        }

        return null;
    }
}
