package com.xepicgamerzx.hotelier.customer_activities.customer_search_activity;

public class DestinationItem {
    private final String cityStateCountry;
    private String placeId;


    public DestinationItem(String cityStateCountry) {
        this.cityStateCountry = cityStateCountry;
    }

    public DestinationItem(String cityStateCountry, String place_id) {
        this.cityStateCountry = cityStateCountry;
        this.placeId = place_id;
    }

    public String getCityStateCountry() {
        return this.cityStateCountry;
    }

    public String getPlaceId() {
        return this.placeId;
    }
}
