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
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.hotel_managers.BedManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.RoomManager;
import com.xepicgamerzx.hotelier.storage.hotel_reference_managers.RoomBedsCrossManager;

import java.util.ArrayList;
import java.util.List;

public class HotelCreatorActivity extends AppCompatActivity {
    HotelManager hotelManager;
    RoomManager roomManager;
    BedManager bedManager;
    RoomBedsCrossManager roomBedsCrossManager;

    Address address;
    List<HotelRoom> hotelRooms = new ArrayList<>();

    TextInputEditText hotelName;
    MaterialButton addAddressBtn;
    MaterialButton addRoomsBtn;
    MaterialButton submitBtn;
    MaterialButton hotelDetails;
    ImageButton backBtn;
    boolean isRoomsMade = false;
    boolean isAddressMade = false;
    boolean isHotelNameMade = false;
    String text = "Hotel Details:";
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    // Amentities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_creator);
        getSupportActionBar().hide();

        // Initializing db
        initializeDb();

        hotelName = findViewById(R.id.hotelNameInput);
        addAddressBtn = findViewById(R.id.addAddressBtn);
        addRoomsBtn = findViewById(R.id.addRoomsBtn);
        submitBtn = findViewById(R.id.saveHotelBtn);
        backBtn = findViewById(R.id.backBtn);
        hotelDetails = findViewById(R.id.hotelDetails);

        hotelDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createHotelInfoDialog();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add star input later
                int starClass = 5;
                String name = hotelName.getText().toString();
                // Do amentities later.
                if (validateHotel()) {
                    hotelManager.createHotel(name, address, starClass, hotelRooms);
                    onBackPressed();
                } else {
                    Toast.makeText(getApplicationContext(), "Missing inputs, try again", Toast.LENGTH_SHORT).show();
                }

            }
        });

        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.hotelCreator, HotelCreateAddressFragment.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        addRoomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.hotelCreator, HotelCreateRoomsFragment.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotelCreatorActivity.super.onBackPressed();
            }
        });
    }

    public void initializeDb() {
        //pretty sure something is causing an error
        hotelManager = HotelManager.getManager(getApplication());
        roomManager = RoomManager.getManager(getApplication());
        bedManager = BedManager.getManager(getApplication());
        roomBedsCrossManager = RoomBedsCrossManager.getManager(getApplication());
    }

    public boolean validateHotel() {
        if (!hotelName.getText().toString().equals("")) {
            isHotelNameMade = true;
        }

        return isAddressMade && isHotelNameMade && isRoomsMade;
    }

    public void createHotelInfoDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View hotelInfo = getLayoutInflater().inflate(R.layout.hotel_details_dialog, null);

        TextView hotelDetails = hotelInfo.findViewById(R.id.hotelDetailsTxt);
        hotelDetails.append(text);


        dialogBuilder.setView(hotelInfo);
        dialog = dialogBuilder.create();
        dialog.show();
    }

}

