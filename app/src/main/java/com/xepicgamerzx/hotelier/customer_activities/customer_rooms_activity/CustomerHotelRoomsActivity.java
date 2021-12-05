package com.xepicgamerzx.hotelier.customer_activities.customer_rooms_activity;

import android.annotation.SuppressLint;
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
import java.util.Objects;

public class CustomerHotelRoomsActivity extends AppCompatActivity {

    HashMap<String, Object> hotelData = new HashMap<>();
    TextView descNameText, hotelAddress, hotelRating;
    RoomManager roomManager;

    @SuppressWarnings("unchecked")
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rooms);
        Objects.requireNonNull(getSupportActionBar()).hide();

        roomManager = RoomManager.getManager(getApplication());
        descNameText = findViewById(R.id.hotelNameDesc);
        hotelAddress = findViewById(R.id.addressTxt);
        hotelRating = findViewById(R.id.ratingTxt);

        // Intent coming from hotelViewAdapter
        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            hotelData = (HashMap<String, Object>) intent.getSerializableExtra("HotelData"); // Gives the hotel object
            HotelViewModel hotelModel = (HotelViewModel) hotelData.get("Hotel");
            Hotel hotel = Objects.requireNonNull(hotelModel).getHotel();
            RecyclerView roomsRecyclerView = findViewById(R.id.roomsRecyclerView);
            setRoomViewText(hotel);

            if (hotelData.containsKey("userStartDate") && hotelData.containsKey("userEndDate")) {
                long userStartDate = (long) hotelData.get("userStartDate");
                long userEndDate = (long) hotelData.get("userEndDate");
                roomsRecyclerView.setAdapter(getAdapterRoomsBySchedule(hotel, userStartDate, userEndDate));
            } else {
                roomsRecyclerView.setAdapter(getAdapterAllRooms(hotel));
            }
            sendCoordToMapFragment(hotel.getAddress().getLatitude(), hotel.getAddress().getLongitude());
        }
    }

    public CustomerHotelRoomsAdapter getAdapterRoomsBySchedule(Hotel hotel, long userStartDate, long userEndDate) {
        List<HotelRoom> hotelRooms = roomManager.getRoomsInHotelByDate(hotel, userStartDate, userEndDate); // Filtered
        List<CustomerHotelRoomsModel> roomViewModel = HotelRoomModelManager.getHotelViewModelList(hotelRooms, getApplication());

        return new CustomerHotelRoomsAdapter(roomViewModel);
    }

    public CustomerHotelRoomsAdapter getAdapterAllRooms(Hotel hotel) {
        List<HotelRoom> hotelRooms = roomManager.getHotelRoomsInHotel(hotel.hotelId); // Not filtered
        List<CustomerHotelRoomsModel> roomViewModel = HotelRoomModelManager.getHotelViewModelList(hotelRooms, getApplication());

        return new CustomerHotelRoomsAdapter(roomViewModel);
    }

    @SuppressLint("SetTextI18n")
    public void setRoomViewText(Hotel hotel) {
        descNameText.setText(hotel.getName());
        hotelAddress.setText("Address: " + hotel.getAddress().getFullStreet());
        hotelRating.setText("Rating: " + hotel.getStarClass() + " Stars");
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