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

    /**
     * Set the street name of the address.
     *
     * @param streetName the street name
     * @return current instance of AddressBuilder
     */
    public AddressBuilder setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    /**
     * Set the postal code of the address.
     *
     * @param postalCode the postal code
     * @return current instance of AddressBuilder
     */
    public AddressBuilder setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * Set the street number of the address
     *
     * @param streetNumber street number
     * @return current instance of AddressBuilder
     */
    public AddressBuilder setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    /**
     * Set the city of the address.
     *
     * @param city the city
     * @return current instance of AddressBuilder
     */
    public AddressBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * Set the province of the address.
     *
     * @param province the province
     * @return current instance of AddressBuilder
     */
    public AddressBuilder setProvince(String province) {
        this.province = province;
        return this;
    }

    /**
     * Set the latitude of the address.
     *
     * @param latitude the latitude
     * @return current instance of AddressBuilder
     */
    public AddressBuilder setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * Set the longitude of the address.
     *
     * @param longitude the longitude
     * @return current instance of AddressBuilder
     */
    public AddressBuilder setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * Build this Address.
     *
     * @return the address
     */
    public Address build() {
        double latCos = Math.cos(latitude * Math.PI / 180);
        double latSin = Math.sin(latitude * Math.PI / 180);
        double lonCos = Math.cos(longitude * Math.PI / 180);
        double lonSin = Math.sin(longitude * Math.PI / 180);

        return new Address(postalCode, streetName, streetNumber, city, province, latitude,
                longitude, latCos, latSin, lonCos, lonSin);
    }
}