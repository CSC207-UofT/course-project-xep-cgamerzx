package com.xepicgamerzx.hotelier.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.BedManager;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.RoomManager;

import java.util.HashMap;
import java.util.List;

public class CustomerHotelRoomsActivity extends AppCompatActivity {

    HashMap<String, Object> hotelData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rooms);
        getSupportActionBar().hide();

        BedManager bedManager = BedManager.getManager(getApplication());
        HotelManager hotelManager = HotelManager.getManager(getApplication());
        RoomManager roomManager = RoomManager.getManager(getApplication());


        TextView descNameText = findViewById(R.id.hotelNameDesc);
        TextView hotelAddress = findViewById(R.id.addressTxt);
        TextView hotelRating = findViewById(R.id.ratingTxt);
        TextView roomsText = findViewById(R.id.customerRoomDetails);

        // Intent coming from hotelViewAdapter
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            hotelData = (HashMap<String, Object>) intent.getSerializableExtra("HotelData"); // Gives the hotel object
            HotelViewModel hotelModel = (HotelViewModel) hotelData.get("Hotel");
            Hotel hotel = hotelModel.getHotel();

            if(hotelData.containsKey("startDate") && hotelData.containsKey("endDate")) {
                long startDate = (long) hotelData.get("startDate");
                long endDate = (long) hotelData.get("endDate");
                List<HotelRoom> roomsBySchedule = roomManager.getRoomsInHotelByDate(hotel, startDate, endDate);
                String roomsDetails = "";
                for(HotelRoom room : roomsBySchedule) {
                    roomsDetails += "\n" + room.toString();
                }
                roomsText.setText(roomsDetails);
            } else {
                List<HotelRoom> hotelRooms = roomManager.getHotelRoomsInHotel(hotel.hotelID);
                String roomsDetails = "";
                for(HotelRoom room : hotelRooms) {
                    roomsDetails += "\n" + room.toString();
                }
                roomsText.setText(roomsDetails);
            }

            descNameText.setText(hotel.getName());
            hotelAddress.setText("Address: " + hotel.getAddress().getFullStreet());
            hotelRating.setText("Rating: " + String.valueOf(hotel.getStarClass()) + " Stars");

            double latitude = hotel.getAddress().getLatitude();
            double longitude = hotel.getAddress().getLongitude();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            MapsFragment fragInfo = new MapsFragment();

            Bundle bundle = new Bundle();
            bundle.putDouble("longitude", longitude);
            bundle.putDouble("latitude", latitude);
            fragInfo.setArguments(bundle);

            fragmentTransaction.replace(R.id.frameMapsLay, fragInfo).commit();

        }
    }
}