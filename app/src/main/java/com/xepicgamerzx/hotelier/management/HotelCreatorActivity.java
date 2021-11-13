package com.xepicgamerzx.hotelier.management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.BedManager;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.RoomManager;
import com.xepicgamerzx.hotelier.storage.dao.ClearTablesDao;

import java.util.ArrayList;
import java.util.List;

public class HotelCreatorActivity extends AppCompatActivity {
    HotelManager hotelManager;
    RoomManager roomManager;
    BedManager bedManager;
    Address address;
    List<HotelRoom> hotelRooms = new ArrayList<>();

    TextInputEditText hotelName;
    MaterialButton addAddressBtn;
    MaterialButton addRoomsBtn;
    MaterialButton submitBtn;
    ImageButton backBtn;

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

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add input verification

                // add star input later
                int starClass = 5;

                // First, create the hotel with rooms
                if (address != null ){
                    hotelManager.createHotel(hotelName.toString(), address, starClass, hotelRooms);
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
        // HotelierDatabase db = Room.databaseBuilder(getApplicationContext(), Database, HotelierDatabase.class).build();
//        HotelierDatabase hotelierDatabase = HotelierDatabase.getDatabase(getApplicationContext());
//        ClearTablesDao clear = hotelierDatabase.clear_tables();
//        clear.nukeTable();

        hotelManager = HotelManager.getManager(getApplication());
        roomManager = RoomManager.getManager(getApplication());
    }


}

