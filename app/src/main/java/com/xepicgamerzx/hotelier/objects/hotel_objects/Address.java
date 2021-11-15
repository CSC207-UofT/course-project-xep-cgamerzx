package com.xepicgamerzx.hotelier.objects.hotel_objects;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {

    public String postalCode;
    private final String streetName;
    private final String streetNumber;
    private final String city;
    private final String province;
    private final double longitude;
    private final double latitude;

    /**
     * Creates a new Address
     *
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

    @NonNull
    @Override
    public String toString() {
        return ("Street Number: " + this.streetNumber +
                "\nStreet Name: " + this.streetName +
                "\nCity: " + this.city +
                "\nProvince: " + this.province +
                "\nPostal Code: " + this.postalCode +
                "\nLongitude: " + this.longitude +
                "\nLatitude: " + this.latitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Double.compare(address.getLongitude(), getLongitude()) == 0 &&
                Double.compare(address.getLatitude(), getLatitude()) == 0 &&
                getStreetName().equals(address.getStreetName()) &&
                getPostalCode().equals(address.getPostalCode()) &&
                getStreetNumber().equals(address.getStreetNumber()) &&
                getCity().equals(address.getCity()) &&
                getProvince().equals(address.getProvince());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreetName(), getPostalCode(), getStreetNumber(), getCity(), getProvince(), getLongitude(), getLatitude());
    }
}


