package com.xepicgamerzx.hotelier.read_dummy_data;

import android.app.Application;
import android.content.Context;

import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.AddressBuilder;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenitiesEnum;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.Manage;
import com.xepicgamerzx.hotelier.storage.hotel_managers.BedManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.RoomManager;
import com.xepicgamerzx.hotelier.storage.hotel_reference_managers.RoomBedsCrossManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to read and populate the database with dummy data
 */
public class ReadDummyData {

    Application application;

    public ReadDummyData(Application application) {
        this.application = application;
        prePopulateHotelAmenities();
    }

    public String loadJsonFromAsset(Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open("dummy_data.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void readData(Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject(loadJsonFromAsset(context));

        JSONArray hotelData = jsonObject.getJSONArray("Hotels");
        HotelManager hotelManager = HotelManager.getManager(application);

        for (int i = 0; i < hotelData.length(); i++) {
            JSONObject hotel = hotelData.getJSONObject(i);
            String name = hotel.getString("Name");
            int rating = hotel.getInt("Rating");
            JSONArray addressArr = hotel.getJSONArray("Address");

            Address address = getAddressFromJsonArray(addressArr);
            JSONArray rooms = hotel.getJSONArray("Rooms");
            List<HotelRoom> hotelRooms = createRoomsInHotel(rooms);

            hotelManager.createHotel(name, address, rating, hotelRooms);
        }
    }

    private List<HotelRoom> createRoomsInHotel(JSONArray rooms) throws JSONException {
        RoomManager roomManager = RoomManager.getManager(application);
        List<HotelRoom> hotelRooms = new ArrayList<>();

        for (int i = 0; i < rooms.length(); i++) {
            JSONObject room_i = rooms.getJSONObject(i);
            JSONArray bedsInRoom = room_i.getJSONArray("beds");
            long startDate = room_i.getLong("startDate");
            long endDate = room_i.getLong("endDate");
            int capacity = room_i.getInt("capacity");
            int pricePerNight = room_i.getInt("pricePerNight");

            HotelRoom room = roomManager.createRoom(ZoneId.systemDefault(), startDate, endDate,
                    capacity, BigDecimal.valueOf(pricePerNight));
            hotelRooms.add(room);
            bedsToRoomReference(bedsInRoom, room);
        }

        return hotelRooms;
    }

    private void bedsToRoomReference(JSONArray beds, HotelRoom room) throws JSONException {
        BedManager bedManager = BedManager.getManager(application);
        RoomBedsCrossManager roomBedsCrossManager = RoomBedsCrossManager.getManager(application);

        for (int i = 0; i < beds.length(); i++) {
            JSONObject bed_i = beds.getJSONObject(i);
            int total = bed_i.getInt("total");
            String type = bed_i.getString("type");

            Bed bed = bedManager.create(type);
            roomBedsCrossManager.createRelationship(room, bed, total);
        }
    }

    private Address getAddressFromJsonArray(JSONArray address) throws JSONException {
        // There should only be one address so this will iterate once.
        JSONObject address_i = address.getJSONObject(0);
        int streetNum = address_i.getInt("streetNum");
        String streetName = address_i.getString("streetName");
        String city = address_i.getString("city");
        String province = address_i.getString("province");
        String postalCode = address_i.getString("postalCode");
        double longitude = address_i.getDouble("longitude");
        double latitude = address_i.getDouble("latitude");

        return new AddressBuilder().setStreetName(streetName).setPostalCode(postalCode).setStreetNumber(String.valueOf(streetNum)).setCity(city).setProvince(province).setLatitude(latitude).setLongitude(longitude).build();
    }

    private void prePopulateHotelAmenities() {
        HotelAmenitiesEnum[] hotelAmenities = HotelAmenitiesEnum.values();

        Manage manage = Manage.getManager(application);
        for (HotelAmenitiesEnum hotelAmenitiesEnum : hotelAmenities) {
            manage.hotelAmenityManager.create(hotelAmenitiesEnum);
        }
    }
}
