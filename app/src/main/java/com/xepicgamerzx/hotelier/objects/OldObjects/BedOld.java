package com.xepicgamerzx.hotelier.objects.OldObjects;

import java.io.Serializable;

@Deprecated
public class BedOld implements Serializable {

    public OldRoom room;
    private String size;

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

    public OldRoom getRoom() {
        if (this.room != null) {
            return this.room;
        }

        return null;
    }

    /**
     * Gives a Bed a reference to the Room it is in.
     *
     * @param room A room object
     */
    public void setRoom(OldRoom room) {
        this.room = room;
    }
}
