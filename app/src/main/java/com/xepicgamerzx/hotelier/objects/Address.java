package com.xepicgamerzx.hotelier.objects;

public class Address {

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


