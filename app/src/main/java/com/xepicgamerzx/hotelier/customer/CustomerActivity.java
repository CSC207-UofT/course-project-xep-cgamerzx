package com.xepicgamerzx.hotelier.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.storage.HotelManager;

public class CustomerActivity extends AppCompatActivity {
    private HotelManager hotelManager;

    private TextView selectedDateRangeText;

    // test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Button datePickerBtn = findViewById(R.id.select_dates_btn);
        Button searchListing = findViewById(R.id.searchListingsBtn);

        selectedDateRangeText = findViewById(R.id.selected_date_range_txt);

        // Getting the HotelManager passed from ActivityMain.
        // Will use to take into account schedules, location in Phase 1.
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            System.out.println("HotelManager Received");
            hotelManager = (HotelManager) intent.getSerializableExtra("HotelManager");
        }


        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("SELECT A CHECK IN AND CHECKOUT DATE");
        final MaterialDatePicker<Pair<Long, Long>> materialDatePicker = builder.build();


        searchListing.setOnClickListener(v -> openListings());

        datePickerBtn.setOnClickListener(v -> materialDatePicker.show(getSupportFragmentManager(), "DATE_RANGE_PICKER"));

        materialDatePicker.addOnPositiveButtonClickListener(selection -> selectedDateRangeText.setText(materialDatePicker.getHeaderText()));
    }

    public void openListings() {
        Intent intent = new Intent(this, HotelListActivity.class).putExtra("HotelManager", hotelManager);
        startActivity(intent);
    }

}