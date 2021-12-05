package com.xepicgamerzx.hotelier.objects.OldObjects;

import java.util.HashMap;
import java.util.List;

@Deprecated
public class OldHotelManager implements IDataBase {
    private IDataBase db;
    private List<HotelOld> hotelsList;

    public HotelOld createHotel(String name, AddressOld address, HashMap<String, OldRoom> rooms) {
        return new HotelOld(name, address, rooms);
    }

    public List<HotelOld> getHotels() {
        return this.hotelsList;
    }

    public void setHotels(List<HotelOld> hotels) {
        this.hotelsList = hotels;
    }

    @Override
    public void save() {
        db.save();
    }

    @Override
    public List<HotelOld> read() {
        this.hotelsList = db.read();
        return this.hotelsList;
    }
}
