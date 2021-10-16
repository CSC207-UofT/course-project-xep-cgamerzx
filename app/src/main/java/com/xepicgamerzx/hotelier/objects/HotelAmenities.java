package com.xepicgamerzx.hotelier.objects;

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

    HotelAmenities(String label){
        this.label = label;
    }
}
