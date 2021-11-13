package com.xepicgamerzx.hotelier.objects.OldObjects;

import java.io.Serializable;

public class BedOld implements Serializable {

    private String size;
    public OldRoom room;

    public BedOld() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    /**
     * Creates a new Bed
     *
     * @param size a string representing the size of the bed
     *             TODO: Precondition on size where you can only have valid strings (King, Queen, ...)
     */
    public BedOld(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    /**
     * Gives a Bed a reference to the Room it is in.
     *
     * @param room A room object
     */
    public void setRoom(OldRoom room) {
        this.room = room;
    }

    public OldRoom getRoom() {
        if (this.room != null) {
            return this.room;
        }

        return null;
    }
}
