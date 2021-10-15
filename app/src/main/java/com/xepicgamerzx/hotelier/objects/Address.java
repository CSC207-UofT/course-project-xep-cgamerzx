package com.xepicgamerzx.hotelier.objects;

public class Address {

    private String streetName;
    private String postalCode;
    private String streetNumber;
    private String city;
    private String province;
    private String longitude;
    private String latitude;

    /**
     * Creates a new Address
     * @param streetName The name of the street
     * @param postalCode The postal code
     */
    public Address(String streetName, String postalCode, String streetNumber, String city,
    String province, String longitude, String latitude) {
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.streetNumber = streetNumber;
        this.city = city;
        this.province = province;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}


