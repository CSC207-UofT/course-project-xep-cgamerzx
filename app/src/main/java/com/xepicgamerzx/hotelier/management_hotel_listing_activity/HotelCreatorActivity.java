package com.xepicgamerzx.hotelier.management_hotel_listing_activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Address;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.Manage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HotelCreatorActivity extends AppCompatActivity {
    String text = "Hotel Details:";
    Manage manage;
    Address address;
    List<HotelRoom> hotelRooms = new ArrayList<>();
    List<HotelAmenity> hotelAmenities = new ArrayList<>();
    TextInputEditText hotelName;
    MaterialButton addAddressBtn, addRoomsBtn, addAmenitiesBtn, submitBtn, hotelDetails;
    ImageButton backBtn;
    boolean isRoomsMade = false, isAddressMade = false, isHotelNameMade = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_creator);
        Objects.requireNonNull(getSupportActionBar()).hide();
        // Initializing db
        initializeDb();
        setCreationFields();
        callAllListeners();
    }

    public void callAllListeners() {
        hotelDetails.setOnClickListener(v -> createHotelInfoDialog());
        backBtn.setOnClickListener(v -> HotelCreatorActivity.super.onBackPressed());
        submitListener();
        addressClickListener();
        roomsClickListener();
        amenitiesClickListener();
    }

    public void submitListener() {
        submitBtn.setOnClickListener(v -> {
            // add star input later
            int starClass = 5;
            String name = Objects.requireNonNull(hotelName.getText()).toString();
            // Do amenities later.
            if (validateHotel()) {
                manage.hotelManager.createHotel(name, address, starClass, hotelRooms);
                onBackPressed();
            } else {
                Toast.makeText(getApplicationContext(), "Missing inputs, try again", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void amenitiesClickListener() {
        addAmenitiesBtn.setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .add(R.id.hotelCreator, HotelCreateAmenitiesFragment.class, null)
                .addToBackStack(null)
                .commit());
    }

    public void addressClickListener() {
        addAddressBtn.setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .add(R.id.hotelCreator, HotelCreateAddressFragment.class, null)
                .addToBackStack(null)
                .commit());
    }

    public void roomsClickListener() {
        addRoomsBtn.setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .add(R.id.hotelCreator, HotelCreateRoomsFragment.class, null)
                .addToBackStack(null)
                .commit());
    }

    public void setCreationFields() {
        hotelName = findViewById(R.id.hotelNameInput);
        addAddressBtn = findViewById(R.id.addAddressBtn);
        addRoomsBtn = findViewById(R.id.addRoomsBtn);
        addAmenitiesBtn = findViewById(R.id.hotelAmenitiesBtn);
        submitBtn = findViewById(R.id.saveHotelBtn);
        backBtn = findViewById(R.id.backBtn);
        hotelDetails = findViewById(R.id.hotelDetails);
    }


    public void initializeDb() {
        //pretty sure something is causing an error
        manage = Manage.getManager(getApplication());
    }

    public boolean validateHotel() {
        if (!Objects.requireNonNull(hotelName.getText()).toString().equals("")) {
            isHotelNameMade = true;
        }

        return isAddressMade && isHotelNameMade && isRoomsMade;
    }

    public void createHotelInfoDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final View hotelInfo = getLayoutInflater().inflate(R.layout.hotel_details_dialog, null);

        TextView hotelDetails = hotelInfo.findViewById(R.id.hotelDetailsTxt);
        hotelDetails.append(text);


        dialogBuilder.setView(hotelInfo);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

}

