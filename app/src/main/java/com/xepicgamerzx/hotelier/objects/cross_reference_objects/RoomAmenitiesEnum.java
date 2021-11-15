package com.xepicgamerzx.hotelier.objects.cross_reference_objects;

import androidx.annotation.NonNull;

import com.xepicgamerzx.hotelier.objects.hotel_objects.LabeledEnum;

/**
 * Enum with all the types of hotelRoom amenities. Each also stores a String label describing the use case.
 */
public enum RoomAmenitiesEnum implements LabeledEnum {
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

    private final String label;

    /**
     * Set each enum constant to have a String label value.
     *
     * @param label String value to be associated with an enum constant.
     */
    RoomAmenitiesEnum(String label) {
        this.label = label;
    }

    @NonNull
    @Override
    public String toString() {
        return this.label;
    }

    /**
     * Gets label of enum.
     *
     * @return String label of enum.
     */
    @Override
    public String getLabel() {
        return this.label;
    }
}
