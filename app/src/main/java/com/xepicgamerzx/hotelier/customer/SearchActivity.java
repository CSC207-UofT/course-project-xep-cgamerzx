package com.xepicgamerzx.hotelier.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.xepicgamerzx.hotelier.MainActivity;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements OnSearchClick {

    int numberOfGuests;
    DestinationItem destinationItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();


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
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        addGuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGuests();
                numGuests.setText(Integer.toString(numberOfGuests));
            }
        });

        minusGuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusGuests();
                numGuests.setText(Integer.toString(numberOfGuests));
            }
        });

        dateSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_RANGE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                Long startDate = selection.first;
                Long endDate = selection.second;

                UnixEpochDateConverter epoch = new UnixEpochDateConverter();
                String dates = epoch.epochToReadable(startDate, endDate);
                dateSelection.setText(dates);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HotelViewActivity.class));
            }
        });
    }

    public void addGuests() {
        this.numberOfGuests += 1;
    }

    public void minusGuests() {
        if(this.numberOfGuests != 1) {
            this.numberOfGuests -= 1;
        }
    }

    @Override
    public void onSearch(DestinationItem destinationItem) {
        // value to receive from AutoDestinationAdapter
        // Is there a better way to just return place_id and call this method rather then doing this. = ...
        this.destinationItem = destinationItem;

    }
}