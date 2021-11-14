package com.xepicgamerzx.hotelier.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.RoomManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelViewActivity extends AppCompatActivity {

    ImageButton backBtn;
    HotelManager hotelManager;
    RoomManager roomManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        getSupportActionBar().hide();

        hotelManager = HotelManager.getManager(getApplication());
        roomManager = RoomManager.getManager(getApplication());

        RecyclerView hotelsRecyclerView = findViewById(R.id.hotelsRecyclerView);
        backBtn = findViewById(R.id.backBtn);
        TextView userGuests = findViewById(R.id.userGuests);
        TextView userCity = findViewById(R.id.userCityText);
        TextView userSchedule = findViewById(R.id.userSchedule);

        Intent intent = getIntent();

        if(intent.getExtras() != null) {
            HashMap<String, Object> map = (HashMap<String, Object>) intent.getSerializableExtra("SearchData");
            String guests = (String) map.get("guests");
            userGuests.setText(guests + " Guests");

            if(map.size() == 1) {
                List<Hotel> hotels = hotelManager.getAll();
                List<HotelViewModel> hotelsView = hotelManager.generateHotelModel(hotels);
                final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(hotelsView);
                hotelsRecyclerView.setAdapter(hotelsAdapter);
            } else {
                String city = (String) map.get("city");
                userCity.setText(city);

                double latitude = (double) map.get("lat");
                double longitude = (double) map.get("long");
                long userStartDate = 0;
                long userEndDate = 0;
                if (map.containsKey("startDate") && map.containsKey("endDate")) {
                    // Send to adapter
                    userStartDate = (long) map.get("startDate");
                    userEndDate = (long) map.get("endDate");
                    UnixEpochDateConverter date = new UnixEpochDateConverter();
                    userSchedule.setText(date.epochToReadable(userStartDate, userEndDate));
                }

                List<Hotel> filterHotels = hotelManager.getHotelsByLatLong(latitude, longitude);
                List<HotelViewModel> filteredHotelsView = hotelManager.generateHotelModel(filterHotels);

                // Giving recycler view only hotels in user destination, and if the user entered a schedule, sending the schedule to adapter.
                if (userStartDate != 0 && userEndDate != 0) {
                    final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(filteredHotelsView, userStartDate, userEndDate);
                    hotelsRecyclerView.setAdapter(hotelsAdapter);
                } else {
                    final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(filteredHotelsView);
                    hotelsRecyclerView.setAdapter(hotelsAdapter);
                }
            }
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                // pop backstack?
            }
        });
    }
}