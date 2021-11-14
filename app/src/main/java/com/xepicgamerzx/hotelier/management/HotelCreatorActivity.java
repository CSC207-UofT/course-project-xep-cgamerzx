package com.xepicgamerzx.hotelier.management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.chip.ChipGroup;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.BedManager;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.RoomManager;

import java.util.ArrayList;
import java.util.List;

public class HotelCreatorActivity extends AppCompatActivity {
    HotelManager hotelManager;
    RoomManager roomManager;
    BedManager bedManager;
    Address address;
    List<HotelRoom> hotelRooms = new ArrayList<>();
    ArrayList<HotelAmenity> hotelAmenities = new ArrayList<>();

    TextInputEditText hotelName;
    MaterialButton addAddressBtn;
    MaterialButton addRoomsBtn;
    TextInputEditText amenityName;
    ChipGroup chipGroup;
    Button addAmenityBtn;
    MaterialButton submitBtn;
    MaterialButton hotelDetails;
    ImageButton backBtn;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    boolean isRoomsMade = false;
    boolean isAddressMade = false;
    boolean isHotelNameMade = false;

    String text = "Hotel Details:";
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
        amenityName = findViewById(R.id.hotelAmentitiesInput);
        addAmenityBtn = findViewById(R.id.addAmenityBtn);
        chipGroup = findViewById(R.id.chipGroup);

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
                    Hotel hotel = hotelManager.createHotel(name, address, starClass, hotelRooms);
                    onBackPressed();
                    for (HotelAmenity amenity : hotelAmenities) {
                        hotelManager.addAmenityToHotel(hotel, amenity);
                    }
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

    public void addAmenityBtnClick(View view) {
        Chip chip = new Chip(this);
        ChipDrawable drawable = ChipDrawable.createFromAttributes(this, null, 0, R.style.Widget_MaterialComponents_Chip_Entry);
        chip.setChipDrawable(drawable);
        chip.setCheckable(false);
        chip.setClickable(false);
        String name = amenityName.getText().toString();
        chip.setText(name);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chipGroup.removeView(chip);
                hotelAmenities.add(hotelManager.createHotelAmenity(name));
            }

        });

        chipGroup.addView(chip);
        amenityName.setText("");
    }

    public void initializeDb() {
        //pretty sure something is causing an error
        hotelManager = HotelManager.getManager(getApplication());
        roomManager = RoomManager.getManager(getApplication());
        bedManager = bedManager.getManager(getApplication());
    }

    public boolean validateHotel() {
        if (!hotelName.getText().toString().equals("")) {
            isHotelNameMade = true;
        }

        if(isAddressMade && isHotelNameMade && isRoomsMade) {
            return true;
        }
        return false;
    }

    public void createHotelInfoDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View hotelInfo = getLayoutInflater().inflate(R.layout.hotel_details_dialog, null);

        TextView hotelDetails = hotelInfo.findViewById(R.id.hotelDetailsTxt);
        text += "\nName: " + hotelName.getText().toString();
        hotelDetails.append(text);


        dialogBuilder.setView(hotelInfo);
        dialog = dialogBuilder.create();
        dialog.show();
    }

}

