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
import com.xepicgamerzx.hotelier.customer_activities.customer_search_activity.api.PlacesAPI;
import com.xepicgamerzx.hotelier.home_page_activities.MainActivity;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;
import com.xepicgamerzx.hotelier.storage.user.UserManager;

import java.util.HashMap;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity implements OnSearchClick {
    int numberOfGuests = 1;
    DestinationItem destinationItem;
    Long startDate, endDate;
    ImageButton addGuestBtn, minusGuestBtn, backBtn;
    TextView numGuests;
    Button dateSelection, searchBtn;
    AutoCompleteTextView editText;
    AutoDestinationAdapter adapter;
    private java.util.Locale Locale;
    UserManager userManager = UserManager.getManager(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Objects.requireNonNull(getSupportActionBar()).hide();

        setAllFields();
        callAllListeners();
    }

    public void callAllListeners() {
        dateSelectorListeners();
        editGuestsListeners();
        searchListeners();
        backOnClickListener();
    }


    public void setAllFields() {
        editText = findViewById(R.id.selectDestination);
        adapter = new AutoDestinationAdapter(getApplicationContext(), this);
        backBtn = findViewById(R.id.backBtn);
        addGuestBtn = findViewById(R.id.btnAdd);
        minusGuestBtn = findViewById(R.id.btnMinus);
        numGuests = findViewById(R.id.textNumGuests);
        dateSelection = findViewById(R.id.dateSelection);
        searchBtn = findViewById(R.id.searchBtn);
        editText.setAdapter(adapter);
    }

    public void backOnClickListener() {
        backBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }

    public void searchListeners() {
        searchBtn.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                HashMap<String, Object> searchData = getUserSearchData();
                addRecentSearches(searchData);
                startActivity(new Intent(getApplicationContext(), HotelViewActivity.class).putExtra("SearchData", searchData));
            });
            thread.start();

        });
    }

    public void addRecentSearches(HashMap<String, Object> searchData) {
        if (searchData.containsKey("city")) {
            userManager.setLastLoggedInUser(getApplicationContext());
            if (userManager.isLoggedIn()) {
                userManager.addRecentSearches((String) searchData.get("city"));
                System.out.println(userManager.getRecentSearches());
            }
        }
    }

    public HashMap<String, Object> getUserSearchData() {
        HashMap<String, Object> searchData = new HashMap<>();
        HashMap<String, Double> coords;
        Double lng = 0D, lat = 0D;
        String destination = null;
        PlacesAPI placesAPI = new PlacesAPI();

        if (destinationItem != null) {
            coords = placesAPI.getLocation(destinationItem.getPlaceId());
            lat = coords.get("latitude");
            lng = coords.get("longitude");
            destination = destinationItem.getCityStateCountry();
        }
        String finalDestination = destination;
        double finalLng = (lng != null) ? lng : 0;
        double finalLat = (lat != null) ? lat : 0;

        searchData.put("guests", numGuests.getText().toString());
        searchData.computeIfAbsent("city", val -> finalDestination);
        searchData.computeIfAbsent("long", val -> {if(finalLng != 0) { return finalLng; } return null;});
        searchData.computeIfAbsent("lat", val -> {if(finalLat != 0) { return finalLat; } return null;});
        searchData.computeIfAbsent("startDate", val -> startDate);
        searchData.computeIfAbsent("endDate", val -> endDate);

        return searchData;
    }

    public void dateSelectorListeners() {
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("SELECT A CHECK IN AND CHECKOUT DATE");
        final MaterialDatePicker<Pair<Long, Long>> materialDatePicker = builder.build();

        dateSelection.setOnClickListener(v -> materialDatePicker.show(getSupportFragmentManager(), "DATE_RANGE_PICKER"));

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            startDate = selection.first;
            endDate = selection.second;

            String dates = UnixEpochDateConverter.epochToReadable(startDate, endDate);
            dateSelection.setText(dates);
        });
    }

    public void editGuestsListeners() {
        addGuestBtn.setOnClickListener(v -> {
            addGuests();
            numGuests.setText(String.format(Locale, "%d", numberOfGuests));
        });

        minusGuestBtn.setOnClickListener(v -> {
            minusGuests();
            numGuests.setText(String.format(Locale, "%d", numberOfGuests));
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

    // OnSearchClick Interface Method.
    @Override
    public void onSearch(DestinationItem destinationItem) {
        // Destination to receive from users search input.
        // DestinationItem comes from a callback method in the AutoDestinationAdapter convertResultString method.
        this.destinationItem = destinationItem;

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}