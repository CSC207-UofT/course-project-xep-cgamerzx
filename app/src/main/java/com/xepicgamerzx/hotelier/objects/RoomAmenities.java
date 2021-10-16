package com.xepicgamerzx.hotelier.objects;

public enum RoomAmenities {
    WIFI("Wi-Fi"),
    ROOM_SERVICE("Room Service"),
    SMOKING("Smoking Room"),
    NONSMOKING("Non-smoking Room"),
    AIR_CONDITIONING("Air conditioning"),
    HOUSEKEEPING("House Keeping"),
    MICROWAVE("Microwave"),
    MINI_FRIDGE("Mini-fridge"),
    COFFEEMAKER("Coffee Maker"),
    UPPER_FLOOR("Upper Floor"),
    GROUND_FLOOR("Ground Floor"),
    WHEELCHAIR_ACCESSIBLE("Wheelchair Accessible"),
    TV("Television"),
    PATIO("Patio"),
    WHIRLPOOL("Whirlpool"),
    SAFE("Safe");

    public final String label;

    RoomAmenities(String label){
        this.label = label;
    }
}
