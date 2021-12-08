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

import java.util.HashMap;
import java.util.Objects;

public class CustomerHotelRoomsActivity extends AppCompatActivity {

    HashMap<String, Object> hotelData = new HashMap<>();
    TextView descNameText, hotelAddress, hotelRating;

    @SuppressWarnings("unchecked")
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rooms);
        Objects.requireNonNull(getSupportActionBar()).hide();

        descNameText = findViewById(R.id.hotelNameDesc);
        hotelAddress = findViewById(R.id.addressTxt);
        hotelRating = findViewById(R.id.ratingTxt);

        // Intent coming from hotelViewAdapter
        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            hotelData = (HashMap<String, Object>) intent.getSerializableExtra("HotelData"); // Gives the hotel object
            HotelViewModel hotelModel = (HotelViewModel) hotelData.get("Hotel");
            RecyclerView roomsRecyclerView = findViewById(R.id.roomsRecyclerView);
            assert hotelModel != null;
            setRoomViewText(hotelModel);

            Long userStartDate = (hotelData.containsKey("userStartDate")) ? (Long) hotelData.get("userStartDate") : null;
            Long userEndDate = (hotelData.containsKey("userEndDate")) ? (Long) hotelData.get("userEndDate") : null;

            roomsRecyclerView.setAdapter(HotelRoomModelManager.getAdapterRooms(hotelModel, getApplication(), userStartDate, userEndDate));
            sendCoordToMapFragment(hotelModel);
        }
    }

    @SuppressLint("SetTextI18n")
    public void setRoomViewText(HotelViewModel hotel) {
        descNameText.setText(hotel.getName());
        hotelAddress.setText("Address: " + hotel.getAddress());
        hotelRating.setText("Rating: " + hotel.getHotelStar() + " Stars");
    }

    public void sendCoordToMapFragment(HotelViewModel hotel) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        MapsFragment fragInfo = new MapsFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble("longitude", hotel.getLongitude());
        bundle.putDouble("latitude", hotel.getLatitude());
        fragInfo.setArguments(bundle);

        fragmentTransaction.replace(R.id.frameMapsLay, fragInfo).commit();
    }
}