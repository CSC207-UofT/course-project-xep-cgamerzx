package com.xepicgamerzx.hotelier.storage;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelAmenities;
import com.xepicgamerzx.hotelier.objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.dao.HotelDao;

import java.io.Serializable;
import java.util.List;

/**
 * A class to manage all the hotels in our database.
 */
public class HotelManager implements Serializable {
    private HotelierDatabase db;
    private HotelDao hotelDao;
    private List<Hotel> allHotels;

    public HotelManager(Application application){
        db = HotelierDatabase.getDatabase(application);
        hotelDao = db.hotelDao();
    }

    @NonNull
    public Hotel createHotel(String name, Address address){
        Hotel hotel = new Hotel(name, address);
        return hotel;
    }

    @NonNull
    public Hotel createHotel(String name, Address address, List<HotelRoom> hotelRooms){
        Hotel hotel = new Hotel(name, address);
        return hotel;
    }

    @NonNull
    public HotelAmenity createHotelAmenity(String amenity_name){
        HotelAmenity hotelAmenity = new HotelAmenity(amenity_name);
        hotelDao.insertHotelWithRooms();
        return hotelAmenity;
    }

    @NonNull
    public HotelAmenity createHotelAmenity(HotelAmenities amenity){
        HotelAmenity hotelAmenity = new HotelAmenity(amenity);
        return hotelAmenity;
    }

    public void addAmenityToHotel(Hotel hotel, HotelAmenity hotelAmenity){

    }

    public List<Hotel> getAllHotels(){
        return hotelDao.getAll();
    }
}
