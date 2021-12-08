package com.xepicgamerzx.hotelier.management_hotel_listing_activity;

import java.util.ArrayList;

public class HotelCreateModel {
    private String postalCode;
    private String streetName;
    private  String streetNumber;
    private  String city;
    private  String province;
    private  double latitude;
    private  double longitude;

    private ArrayList<Long> roomIds = new ArrayList<>();

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<Long> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(ArrayList<Long> roomIds) {
        this.roomIds = roomIds;
    }

    public void addRoomId(long roomId){
        this.roomIds.add(roomId);
    }

    public String addressToString(){
        return ("Street Number: " + this.streetNumber +
                "\nStreet Name: " + this.streetName +
                "\nCity: " + this.city +
                "\nProvince: " + this.province +
                "\nPostal Code: " + this.postalCode +
                "\nLatitude: " + this.latitude +
                "\nLongitude: " + this.longitude);
    }
}
