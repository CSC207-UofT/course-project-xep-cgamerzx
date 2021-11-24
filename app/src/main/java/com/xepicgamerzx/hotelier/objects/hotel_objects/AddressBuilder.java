package com.xepicgamerzx.hotelier.objects.hotel_objects;

/**
 * Builder for address
 */
public class AddressBuilder {
    private String streetName;
    private String postalCode;
    private String streetNumber;
    private String city;
    private String province;
    private double latitude;
    private double longitude;

    public AddressBuilder setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public AddressBuilder setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressBuilder setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public AddressBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder setProvince(String province) {
        this.province = province;
        return this;
    }

    public AddressBuilder setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public AddressBuilder setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Address build() {
        double latCos = Math.cos(latitude * Math.PI / 180);
        double latSin = Math.sin(latitude * Math.PI / 180);
        double lonCos = Math.cos(longitude * Math.PI / 180);
        double lonSin = Math.sin(longitude * Math.PI / 180);

        return new Address(postalCode, streetName, streetNumber, city, province, latitude, longitude, latCos, latSin, lonCos, lonSin);
    }
}