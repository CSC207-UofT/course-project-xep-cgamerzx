package com.xepicgamerzx.hotelier.customer_activities.customer_rooms_activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewModel;
import com.xepicgamerzx.hotelier.customer_activities.maps_fragment.MapsFragment;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.hotel_managers.RoomManager;

import java.util.HashMap;
import java.util.List;

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
            hotelRating.setText("Rating: " + hotel.getStarClass() + " Stars");

            if (hotelData.containsKey("userStartDate") && hotelData.containsKey("userEndDate")) {
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