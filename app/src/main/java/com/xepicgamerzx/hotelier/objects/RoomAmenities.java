package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;

/**
 * Enum with all the types of hotelRoom amenities. Each also stores a String label describing the use case.
 */
public enum RoomAmenities {
    WIFI("Wi-Fi"),
    ROOM_SERVICE("HotelRoom Service"),
    SMOKING("Smoking HotelRoom"),
    NONSMOKING("Non-smoking HotelRoom"),
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

    /**
     * Set each enum constant to have a String label value.
     *
     * @param label String value to be associated with an enum constant.
     */
    RoomAmenities(String label) {
        this.label = label;
    }

    @NonNull
    @Override
    public String toString() {
        return this.label;
    }
}
