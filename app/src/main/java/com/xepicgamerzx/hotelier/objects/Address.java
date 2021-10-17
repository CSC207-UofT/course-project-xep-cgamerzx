package com.xepicgamerzx.hotelier.objects;

import java.io.Serializable;

public class Address implements Serializable {

    private String streetName;
    public String postalCode;
    private String streetNumber;
    private String city;
    private String province;
    private double longitude;
    private double latitude;

    /**
     * Creates a new Address
     * @param streetName The name of the street
     * @param postalCode The postal code
     */
    public Address(String streetName, String postalCode, String streetNumber, String city,
    String province, double longitude, double latitude) {
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.streetNumber = streetNumber;
        this.city = city;
        this.province = province;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getStreetNumber() {
        return this.streetNumber;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getFullStreet() {
        return String.format("%s %s", this.streetNumber, this.streetName);
    }

    @Override
    public String toString() {
        String sf = ("Street Number: " + this.streetNumber +
                        "\nStreet Name: " + this.streetName +
                        "\nCity: " + this.city +
                        "\nProvince: " + this.province +
                        "\nPostal Code: " + this.postalCode +
                        "\nLongitude: " + this.longitude +
                        "\nLatitude: " + this.latitude);
        return sf;
    }
}


