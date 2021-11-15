package com.xepicgamerzx.hotelier.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.RoomBedsCrossRef;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;
import com.xepicgamerzx.hotelier.storage.BedManager;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.RoomBedsCrossManager;
import com.xepicgamerzx.hotelier.storage.RoomManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CustomerHotelRoomsActivity extends AppCompatActivity {

    HashMap<String, Object> hotelData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rooms);
        getSupportActionBar().hide();

        RoomManager roomManager = RoomManager.getManager(getApplication());
        TextView descNameText = findViewById(R.id.hotelNameDesc);
        TextView hotelAddress = findViewById(R.id.addressTxt);
        TextView hotelRating = findViewById(R.id.ratingTxt);

        // Intent coming from hotelViewAdapter
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            hotelData = (HashMap<String, Object>) intent.getSerializableExtra("HotelData"); // Gives the hotel object
            HotelViewModel hotelModel = (HotelViewModel) hotelData.get("Hotel");
            Hotel hotel = hotelModel.getHotel();
            List<CustomerHotelRoomsModel> roomViewModel;
            RecyclerView roomsRecyclerView = findViewById(R.id.roomsRecyclerView);

            descNameText.setText(hotel.getName());
            hotelAddress.setText("Address: " + hotel.getAddress().getFullStreet());
            hotelRating.setText("Rating: " + String.valueOf(hotel.getStarClass()) + " Stars");

            if(hotelData.containsKey("userStartDate") && hotelData.containsKey("userEndDate")) {
                long userStartDate = (long) hotelData.get("userStartDate");
                long userEndDate = (long) hotelData.get("userEndDate");
                List<HotelRoom> hotelRooms = roomManager.getRoomsInHotelByDate(hotel, userStartDate, userEndDate); // Filtered
                roomViewModel = HotelRoomModelManager.getHotelViewModelList(hotelRooms, getApplication());
                final CustomerHotelRoomsAdapter hotelRoomsAdapter = new CustomerHotelRoomsAdapter(roomViewModel);
                roomsRecyclerView.setAdapter(hotelRoomsAdapter);

            } else {
                List<HotelRoom> hotelRooms = roomManager.getHotelRoomsInHotel(hotel.hotelID); // Not filtered
                roomViewModel = HotelRoomModelManager.getHotelViewModelList(hotelRooms, getApplication());
                final CustomerHotelRoomsAdapter hotelRoomsAdapter = new CustomerHotelRoomsAdapter(roomViewModel);
                roomsRecyclerView.setAdapter(hotelRoomsAdapter);
            }

            sendCoordToMapFragment(hotel.getAddress().getLatitude(), hotel.getAddress().getLongitude());
        }
    }

    public void sendCoordToMapFragment(double latitude, double longitude) {
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