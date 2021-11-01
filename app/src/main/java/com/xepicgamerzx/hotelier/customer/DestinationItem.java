package com.xepicgamerzx.hotelier.customer;

public class DestinationItem {
    private String city;
    private String state;
    private String country;


    public DestinationItem(String city, String state, String country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getDestination() {
        return String.format("%s, %s, %s", this.city, this.state, this.country);
    }
}
