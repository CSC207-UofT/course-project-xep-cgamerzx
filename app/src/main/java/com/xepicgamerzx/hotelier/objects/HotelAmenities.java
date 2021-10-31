package com.xepicgamerzx.hotelier.objects;

import androidx.annotation.NonNull;

/**
 * Enum with all the types of hotel amenities. Each also stores a String label describing the use case.
 */
public enum HotelAmenities {
    INDOOR_POOL("Indoor Pool"),
    OUTDOOR_POOL("Outdoor Pool"),
    GYM("Gym"),
    LAUNDRY("Laundry"),
    BUSINESS_SERVICES("Business Services"),
    WEDDING_SERVICES("Wedding Services"),
    CONFERENCE_SPACE("Conference Space"),
    SMOKE_FREE("Smoke Free Property"),
    BAR("Bar"),
    COMPLIMENTARY_BREAKFAST("Complimentary Breakfast"),
    TFS_FRONT_DESK("24/7 Front Desk"),
    PARKING_INCLUDED("Parking Included"),
    RESTAURANT("Restaurant"),
    SPA("Spa"),
    ELEVATOR("Elevator"),
    ATM_BANK("ATM/Banking Services"),
    FRONT_DESK_SAFE("Front Desk Safe");


    public final String label;

    /**
     * Set each enum constant to have a String label value.
     *
     * @param label String value to be associated with an enum constant.
     */
    HotelAmenities(String label) {
        this.label = label;
    }

    @NonNull
    @Override
    public String toString() {
        return this.label;
    }
}
