package com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer_activities.customer_search_activity.SearchActivity;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelViewActivity extends AppCompatActivity {

    ImageButton backBtn;
    Manage manage;
    HotelierDatabase hotelierDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        getSupportActionBar().hide();

        manage = Manage.getManager(getApplication());
        hotelierDatabase = HotelierDatabase.getDatabase(getApplication());

        RecyclerView hotelsRecyclerView = findViewById(R.id.hotelsRecyclerView);
        backBtn = findViewById(R.id.backBtn);
        TextView userGuests = findViewById(R.id.userGuests);
        TextView userCity = findViewById(R.id.userCityText);
        TextView userSchedule = findViewById(R.id.userSchedule);

        Intent intent = getIntent();

        // BELOW IS SOME MESSY CODE, WILL CLEAN LATER BUT WHATS IMPORTANT IS THAT IT WORKS FOR NOW.
        if (intent.getExtras() != null) {
            HashMap<String, Object> map = (HashMap<String, Object>) intent.getSerializableExtra("SearchData");
            String guests = (String) map.get("guests");
            long userStartDate = 0;
            long userEndDate = 0;
            List<Hotel> hotels = hotelierDatabase.hotelDao().getAll();
            List<HotelViewModel> hotelsView = manage.hotelManager.generateHotelModel(hotels);
            userGuests.setText(guests + " Guests");

            if (map.size() == 1) {
                final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(hotelsView);
                hotelsRecyclerView.setAdapter(hotelsAdapter);
            } else if (map.size() == 3) {
                if (map.containsKey("startDate") && map.containsKey("endDate")) {
                    userStartDate = (long) map.get("startDate");
                    userEndDate = (long) map.get("endDate");
                    UnixEpochDateConverter date = new UnixEpochDateConverter();
                    userSchedule.setText(UnixEpochDateConverter.epochToReadable(userStartDate, userEndDate));

                    List<HotelViewModel> filterHotelsByUserSchedule = new ArrayList<>();
                    for (HotelViewModel hotelModel : hotelsView) {
                        if (manage.roomManager.isUserScheduleInHotel(hotelModel.getHotel(), userStartDate, userEndDate)) {
                            filterHotelsByUserSchedule.add(hotelModel);
                        }
                    }
                    final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(filterHotelsByUserSchedule, userStartDate, userEndDate);
                    hotelsRecyclerView.setAdapter(hotelsAdapter);
                }
            } else {
                String city = (String) map.get("city");
                userCity.setText(city);
                double longitude;
                double latitude;
                latitude = (double) map.get("lat");
                longitude = (double) map.get("long");

                if (map.containsKey("startDate") && map.containsKey("endDate")) {
                    // Send to adapter
                    userStartDate = (long) map.get("startDate");
                    userEndDate = (long) map.get("endDate");
                    userSchedule.setText(UnixEpochDateConverter.epochToReadable(userStartDate, userEndDate));
                }

                List<Hotel> filterHotels = manage.hotelManager.getHotelsInArea(latitude, longitude);
                List<HotelViewModel> filteredHotelsByCity = manage.hotelManager.generateHotelModel(filterHotels);

                // Giving recycler view only hotels in user destination, and if the user entered a schedule, sending the schedule to adapter.
                if (userStartDate != 0 && userEndDate != 0) {
                    List<HotelViewModel> filterHotelsByScheduleAndCity = new ArrayList<>();
                    for (HotelViewModel hotelViewModel : filteredHotelsByCity) {
                        if (manage.roomManager.isUserScheduleInHotel(hotelViewModel.getHotel(), userStartDate, userEndDate)) {
                            filterHotelsByScheduleAndCity.add(hotelViewModel);
                        }
                    }
                    final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(filterHotelsByScheduleAndCity, userStartDate, userEndDate);
                    hotelsRecyclerView.setAdapter(hotelsAdapter);
                } else {
                    final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(filteredHotelsByCity);
                    hotelsRecyclerView.setAdapter(hotelsAdapter);
                }
            }
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
    }
}