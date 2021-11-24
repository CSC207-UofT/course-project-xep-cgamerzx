package com.xepicgamerzx.hotelier.objects.hotel_objects;

import androidx.annotation.NonNull;
import androidx.room.Ignore;

import java.io.Serializable;

public class Address implements Serializable {

    private final String postalCode;
    private final String streetName;
    private final String streetNumber;
    private final String city;
    private final String province;
    private final double longitude;
    private final double latitude;
    private final double lonCos;
    private final double lonSin;
    private final double latCos;
    private final double latSin;

    /**
     * Create a new address
     *
     * @param streetName String name of street
     * @param postalCode String postal code
     * @param streetNumber String street number
     * @param city String city name
     * @param province String province/state name
     * @param longitude double longitude
     * @param latitude double latitude
     */
    @Ignore
    public Address(String streetName, String postalCode, String streetNumber, String city,
                   String province, double longitude, double latitude) {
        this(postalCode, streetName, streetNumber, city, province, longitude, latitude,
                Math.cos(latitude * Math.PI / 180),
                Math.sin(latitude * Math.PI / 180),
                Math.cos(latitude * Math.PI / 180),
                Math.sin(latitude * Math.PI / 180));
    }

    /**
     * Create a new address
     *
     * @param streetName String name of street
     * @param postalCode String postal code
     * @param streetNumber String street number
     * @param city String city name
     * @param province String province/state name
     * @param longitude double longitude
     * @param latitude double latitude
     * @param lonCos double cosine representation of longitude
     * @param lonSin double sine representation of longitude
     * @param latCos double cosine representation of latitude
     * @param latSin double sine representation of latitude
     */
    public Address(String postalCode, String streetName, String streetNumber, String city, String province, double longitude, double latitude, double lonCos, double lonSin, double latCos, double latSin) {
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.province = province;
        this.longitude = longitude;
        this.latitude = latitude;
        this.lonCos = lonCos;
        this.lonSin = lonSin;
        this.latCos = latCos;
        this.latSin = latSin;
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

    public double getLonCos() {
        return lonCos;
    }

    public double getLonSin() {
        return lonSin;
    }

    public double getLatCos() {
        return latCos;
    }

    public double getLatSin() {
        return latSin;
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
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (Double.compare(address.getLongitude(), getLongitude()) != 0) return false;
        if (Double.compare(address.getLatitude(), getLatitude()) != 0) return false;
        if (Double.compare(address.getLonCos(), getLonCos()) != 0) return false;
        if (Double.compare(address.getLonSin(), getLonSin()) != 0) return false;
        if (Double.compare(address.getLatCos(), getLatCos()) != 0) return false;
        if (Double.compare(address.getLatSin(), getLatSin()) != 0) return false;
        if (getPostalCode() != null ? !getPostalCode().equals(address.getPostalCode()) : address.getPostalCode() != null)
            return false;
        if (getStreetName() != null ? !getStreetName().equals(address.getStreetName()) : address.getStreetName() != null)
            return false;
        if (getStreetNumber() != null ? !getStreetNumber().equals(address.getStreetNumber()) : address.getStreetNumber() != null)
            return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null)
            return false;
        return getProvince() != null ? getProvince().equals(address.getProvince()) : address.getProvince() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getPostalCode() != null ? getPostalCode().hashCode() : 0;
        result = 31 * result + (getStreetName() != null ? getStreetName().hashCode() : 0);
        result = 31 * result + (getStreetNumber() != null ? getStreetNumber().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getProvince() != null ? getProvince().hashCode() : 0);
        temp = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLatitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLonCos());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLonSin());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLatCos());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLatSin());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}


