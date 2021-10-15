package com.xepicgamerzx.hotelier.objects;

public class Bed {

    private final String size;
    private final Room room;
    /**
     * Creates a new Bed
     * @param size a string representing the size of the bed
     * @param room the room this bed is in
     * TODO: Precondition on size where you can only have valid strings (King, Queen, ...)
     */
    public Bed(String size, Room room) {
        this.size = size;
        this.room = room;
    }
}
