package com.xepicgamerzx.hotelier.customer_activities.customer_search_activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewActivity;
import com.xepicgamerzx.hotelier.home_page_activities.MainActivity;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;

import java.util.HashMap;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity implements OnSearchClick {

    int numberOfGuests = 1;
    DestinationItem destinationItem;
    Long startDate;
    Long endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Objects.requireNonNull(getSupportActionBar()).hide();

        AutoCompleteTextView editText = findViewById(R.id.selectDestination);
        AutoDestinationAdapter adapter = new AutoDestinationAdapter(getApplicationContext(), this);
        editText.setAdapter(adapter);

        ImageButton backBtn = findViewById(R.id.backBtn);
        ImageButton addGuestBtn = findViewById(R.id.btnAdd);
        ImageButton minusGuestBtn = findViewById(R.id.btnMinus);
        TextView numGuests = findViewById(R.id.textNumGuests);
        Button dateSelection = findViewById(R.id.dateSelection);
        Button searchBtn = findViewById(R.id.searchBtn);

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("SELECT A CHECK IN AND CHECKOUT DATE");
        final MaterialDatePicker<Pair<Long, Long>> materialDatePicker = builder.build();

        // Listeners
        backBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        addGuestBtn.setOnClickListener(v -> {
            addGuests();
            numGuests.setText(Integer.toString(numberOfGuests));
        });

        minusGuestBtn.setOnClickListener(v -> {
            minusGuests();
            numGuests.setText(Integer.toString(numberOfGuests));
        });

        dateSelection.setOnClickListener(v -> materialDatePicker.show(getSupportFragmentManager(), "DATE_RANGE_PICKER"));

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            startDate = selection.first;
            endDate = selection.second;

            String dates = UnixEpochDateConverter.epochToReadable(startDate, endDate);
            dateSelection.setText(dates);
        });

        searchBtn.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                HashMap<String, Object> searchData = new HashMap<>();
                searchData.put("guests", numGuests.getText().toString());

                PlacesAPI placesAPI = new PlacesAPI();
                if (destinationItem != null && startDate != null && endDate != null) {
                    HashMap<String, Double> coords = placesAPI.getLocation(destinationItem.getPlaceId());
                    searchData.put("city", destinationItem.getCityStateCountry());
                    searchData.put("long", coords.get("longitude"));
                    searchData.put("lat", coords.get("latitude"));
                    searchData.put("startDate", startDate);
                    searchData.put("endDate", endDate);
                } else if (destinationItem == null && (startDate != null && endDate != null)) {
                    searchData.put("startDate", startDate);
                    searchData.put("endDate", endDate);
                } else if (destinationItem != null && (startDate == null && endDate == null)) {
                    // User enters destination, but no schedule
                    HashMap<String, Double> coords = placesAPI.getLocation(destinationItem.getPlaceId());
                    searchData.put("city", destinationItem.getCityStateCountry());
                    searchData.put("long", coords.get("longitude"));
                    searchData.put("lat", coords.get("latitude"));
                }
                startActivity(new Intent(getApplicationContext(), HotelViewActivity.class).putExtra("SearchData", searchData));
            });
            thread.start();

        });
    }

    public void addGuests() {
        this.numberOfGuests += 1;
    }

    public void minusGuests() {
        if (this.numberOfGuests != 1) {
            this.numberOfGuests -= 1;
        }
    }

    // Interface method
    @Override
    public void onSearch(DestinationItem destinationItem) {
        // value to receive from AutoDestinationAdapter
        // Is there a better way to just return place_id and call this method rather then doing this. = ...
        this.destinationItem = destinationItem;

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}