package com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer_activities.customer_search_activity.SearchActivity;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class HotelViewActivity extends AppCompatActivity {

    ImageButton backBtn;
    Manage manage;
    HotelierDatabase hotelierDatabase;

    /**
     * The method that is run when the page loads.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        Objects.requireNonNull(getSupportActionBar()).hide();

        manage = Manage.getManager(getApplication());
        hotelierDatabase = HotelierDatabase.getDatabase(getApplication());

        RecyclerView hotelsRecyclerView = findViewById(R.id.hotelsRecyclerView);
        backBtn = findViewById(R.id.backBtn);
        TextView userGuests = findViewById(R.id.userGuests);
        TextView userCity = findViewById(R.id.userCityText);
        TextView userSchedule = findViewById(R.id.userSchedule);

        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            HashMap<String, Object> map = (HashMap<String, Object>) intent.getSerializableExtra("SearchData");
            List<HotelViewModel> hotelsView;
            HotelViewAdapter hotelsAdapter;

            Double latitude = (map.containsKey("lat")) ? (Double) map.get("lat") : null;
            Double longitude = (map.containsKey("long")) ? (Double) map.get("long") : null;

            Long startDate = (map.containsKey("startDate")) ? (Long) map.get("startDate") : null;
            Long endDate = (map.containsKey("endDate")) ? (Long) map.get("endDate") : null;

            String guests = (String) map.get("guests");
            String str = guests.toString() + " Guests";
            userGuests.setText(str);
            int minCapacity = (guests != null) ? Integer.parseInt(guests) : 1;

            if (map.size() == 1) {// Capacity only
                hotelsView = manage.hotelManager.generateHotelModel(minCapacity);
                hotelsAdapter = new HotelViewAdapter(hotelsView);
            } else {
                if (endDate != null && startDate != null) {
                    // Schedule
                    userSchedule.setText(UnixEpochDateConverter.epochToReadable(startDate, endDate));
                    if (latitude != null && longitude != null) {
                        // Schedule and Location
                        String city = (String) map.get("city");
                        userCity.setText(city);

                        hotelsView = manage.hotelManager.generateHotelModel(minCapacity, startDate, endDate, latitude, longitude);
                    } else {
                        // Schedule Only
                        hotelsView = manage.hotelManager.generateHotelModel(minCapacity, startDate, endDate);
                    }
                    hotelsAdapter = new HotelViewAdapter(hotelsView, startDate, endDate);
                } else if (latitude != null && longitude != null) {
                    // Location Only
                    String city = (String) map.get("city");
                    userCity.setText(city);
                    hotelsView = manage.hotelManager.generateHotelModel(minCapacity, latitude, longitude);
                    hotelsAdapter = new HotelViewAdapter(hotelsView);
                } else {
                    // Fallback
                    Log.e("Hotel View Activity", "Unexpected type of parameters for search. Defaulting to all.");
                    hotelsView = manage.hotelManager.generateHotelModel();
                    hotelsAdapter = new HotelViewAdapter(hotelsView);
                }
            }
            hotelsRecyclerView.setAdapter(hotelsAdapter);
        }
        backBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SearchActivity.class)));
    }


    /**
     * Logic for when a user clicks the back button on their android phone.
     */
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
    }
}