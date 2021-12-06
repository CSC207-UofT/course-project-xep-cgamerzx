package com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer_activities.customer_search_activity.SearchActivity;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;

import java.util.Arrays;
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
            List<Hotel> hotels;
            List<HotelViewModel> hotelsView;
            HotelViewAdapter hotelsAdapter;

            String guests = (String) map.get("guests");
            String str = guests + R.string._guests;
            userGuests.setText(str);
            int minCapacity = (guests != null) ? Integer.parseInt(guests) : 1;

            switch (map.size()) {
                case 1:
                    // Capacity only
                    List<Long> hotelIds = hotelierDatabase.roomDao().getAvailableHotelIds(minCapacity);
                    hotels = hotelIdsToHotel(hotelIds);
                    hotelsView = manage.hotelManager.generateHotelModel(hotels);
                    hotelsAdapter = new HotelViewAdapter(hotelsView);
                    break;
                case 3:
                    // Schedule and Capacity only
                case 4:
                    // Location and Capacity only
                case 6:
                    // Location and schedule and Capacity
                    hotels = filterByLocation(userCity, map);
                    hotelsAdapter = filterBySchedule(userSchedule, map, minCapacity, hotels);
                    break;
                default:
                    Log.e("Hotel View Activity", "Unexpected number of parameters for search. Defaulting to all.");
                    hotels = hotelierDatabase.hotelDao().getAll();
                    hotelsView = manage.hotelManager.generateHotelModel(hotels);
                    hotelsAdapter = new HotelViewAdapter(hotelsView);
            }
            hotelsRecyclerView.setAdapter(hotelsAdapter);
        }
        backBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SearchActivity.class)));
    }

    /**
     * Filter by the location that a user entered.
     * @param userCity The city a user entered
     * @param map The hashmap containing the various data that a user entered.
     * @return List<Hotel>
     */
    private List<Hotel> filterByLocation(TextView userCity, HashMap<String, Object> map) {
        if (map.containsKey("city") && map.containsKey("lat") && map.containsKey("long")) {
            String city = (String) map.get("city");
            userCity.setText(city);
            Double latitude = (Double) map.get("lat");
            Double longitude = (Double) map.get("long");
            if (latitude != null && longitude != null)
                return manage.hotelManager.getHotelsInArea(latitude, longitude);
        }
        Log.i("Hotel View Activity", "Can't sort by location.");
        return hotelierDatabase.hotelDao().getAll();
    }

    /**
     * Filter by the user's schedule, given that they entered one. Return the adapter with the filtered hotels.
     * @param userSchedule The schedule a user entered
     * @param map The hashmap containing the data.
     * @param minCapacity The minimum capacity a user entered
     * @param hotels The hotels being filtered.
     * @return HotelViewAdapter
     */
    @NonNull
    private HotelViewAdapter filterBySchedule(TextView userSchedule, HashMap<String, Object> map, int minCapacity, List<Hotel> hotels) {
        long[] arr = new long[hotels.size()];
        Arrays.setAll(arr, index -> hotels.get(index).hotelId);

        if (map.containsKey("startDate") && map.containsKey("endDate")) {
            Long startDate = (Long) map.get("startDate");
            Long endDate = (Long) map.get("endDate");
            if (endDate != null && startDate != null) {
                userSchedule.setText(UnixEpochDateConverter.epochToReadable(startDate, endDate));

                List<Long> hotelIds = hotelierDatabase.roomDao().getAvailableHotelIds(startDate, endDate, minCapacity, arr);
                List<Hotel> filteredHotels = hotelIdsToHotel(hotelIds);
                List<HotelViewModel> hotelsView = manage.hotelManager.generateHotelModel(filteredHotels);
                return new HotelViewAdapter(hotelsView, startDate, endDate);
            }
        }
        Log.i("Hotel View Activity", "Can't sort by schedule.");
        List<Long> hotelIds = hotelierDatabase.roomDao().getAvailableHotelIds(minCapacity, arr);
        List<Hotel> filteredHotels = hotelIdsToHotel(hotelIds);
        List<HotelViewModel> hotelsView = manage.hotelManager.generateHotelModel(filteredHotels);
        return new HotelViewAdapter(hotelsView);
    }

    /***
     * Filter hotel id's.
     * @param hotelIds The hotel id's attached to each hotel.
     * @return A list of hotels matching the id's.
     */
    private List<Hotel> hotelIdsToHotel(List<Long> hotelIds) {
        Long[] filteredHotelIds = new Long[hotelIds.size()];
        Arrays.setAll(filteredHotelIds, hotelIds::get);

        return hotelierDatabase.hotelDao().getIdMatch(filteredHotelIds);
    }

    /**
     * Logic for when a user clicks the back button on their android phone.
     */
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
    }
}