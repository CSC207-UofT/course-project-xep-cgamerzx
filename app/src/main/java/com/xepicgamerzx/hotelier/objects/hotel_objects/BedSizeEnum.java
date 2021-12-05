package com.xepicgamerzx.hotelier.objects.hotel_objects;

import androidx.annotation.NonNull;

/**
 * Enum with all the types of bed sizes. Each also stores a String label describing the use case.
 */
public enum BedSizeEnum implements LabeledEnum {
    QUEEN("Queen"),
    TWIN("Twin"),
    KING("King"),
    DOUBLE("Double");

    private final String label;

    /**
     * Set each enum constant to have a String label value.
     *
     * @param label String value to be associated with an enum constant.
     */
    BedSizeEnum(String label) {
        this.label = label;
    }

    /**
     * Gets the string value of enum
     *
     * @return String value of enum
     */
    @Override
    @NonNull
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
