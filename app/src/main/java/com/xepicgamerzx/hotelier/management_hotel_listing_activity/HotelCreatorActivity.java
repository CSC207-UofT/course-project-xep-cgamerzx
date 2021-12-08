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
import com.xepicgamerzx.hotelier.storage.Manage;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelIdBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HotelCreatorActivity extends AppCompatActivity {
    String text = "Hotel Details:";
    Manage manage;
    List<String> hotelAmenities = new ArrayList<>();
    TextInputEditText hotelName;
    MaterialButton addAddressBtn, addRoomsBtn, addAmenitiesBtn, submitBtn, hotelDetails;
    ImageButton backBtn;
    HotelCreateModel viewModel;
    boolean isRoomsMade = false, isAddressMade = false, isHotelNameMade = false;

    boolean[] selectedAmenity;
    ArrayList<Integer> amenitiesList = new ArrayList<>();
    String[] amenitiesArray = {"Indoor Pool", "Outdoor Pool", "Gym", "Laundry",
            "Business Services", "Wedding Services", "Conference Space", "Smoke Free Property",
            "Bar", "Complementary Breakfast", "24/7 Front Desk", "Parking Included", "Restaurant",
            "Spa", "Elevator", "ATM/Banking Services", "Front Desk Safe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_creator);
        Objects.requireNonNull(getSupportActionBar()).hide();
        viewModel = new HotelCreateModel();

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
        hotelDetails.setOnClickListener(v -> createHotelInfoDialog());

        submitBtn.setOnClickListener(v -> {
            // add star input later
            int starClass = 5;
            String name = Objects.requireNonNull(hotelName.getText()).toString();
            if (validateHotel()) {
                hotelAmenities = createAmenities(amenitiesArray, amenitiesList);
                new HotelIdBuilder()
                        .setAmenityIds((ArrayList<String>) hotelAmenities)
                        .setCity(viewModel.getCity())
                        .setLatitude(viewModel.getLatitude())
                        .setLongitude(viewModel.getLongitude())
                        .setName(name)
                        .setPostalCode(viewModel.getPostalCode())
                        .setProvince(viewModel.getProvince())
                        .setStarClass(starClass)
                        .setRoomIds(viewModel.getRoomIds())
                        .setStreetNumber(viewModel.getStreetNumber())
                        .setStreetName(viewModel.getStreetName())
                        .buildHotelId(getApplication());
                onBackPressed();
            } else {
                Toast.makeText(getApplicationContext(), "Missing inputs, try again", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void amenitiesClickListener() {
        //Create a dropdown menu to select amenities
        //Initialize selected amenity array
        selectedAmenity = new boolean[amenitiesArray.length];

        addAmenitiesBtn.setOnClickListener(v -> {
            //Initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    HotelCreatorActivity.this
            );
            //Set title
            builder.setTitle("Select Amenities");
            //Set dialog non cancelable
            builder.setCancelable(false);

            builder.setMultiChoiceItems(amenitiesArray, selectedAmenity, (dialog, which, isChecked) -> {
                //Check condition
                if (isChecked) {
                    amenitiesList.add(which);
                    Collections.sort(amenitiesList);
                } else {
                    //When checkbox is unchecked, remove from list
                    amenitiesList.remove((Integer) which);
                }
            });

            builder.setPositiveButton("Confirm", (dialog, which) -> {
                StringBuilder stringBuilder = new StringBuilder();

                if (amenitiesList.isEmpty()) {
                    stringBuilder.append("Add amenities");
                } else {
                    for (int i = 0; i < amenitiesList.size(); i++) {
                        //Concatenate value
                        stringBuilder.append(amenitiesArray[amenitiesList.get(i)]);
                        //Check condition
                        if (i != amenitiesList.size() - 1) {
                            stringBuilder.append(", ");
                        }
                    }
                }
                //Set text on text view
                addAmenitiesBtn.setText(stringBuilder.toString());
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> {
                //Dismiss dialog
                dialog.dismiss();
            });

            builder.setNeutralButton("Clear All", (dialog, which) -> {
                for (int i = 0; i < selectedAmenity.length; i++) {
                    //Remove all selection
                    selectedAmenity[i] = false;
                    amenitiesList.clear();
                    addAmenitiesBtn.setText(R.string.add_amenities);
                }
            });

            builder.show();
        });
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

    public ArrayList<String> createAmenities(String[] amenities, ArrayList<Integer> indices) {
        ArrayList<String> hotelAmenities = new ArrayList<>();

        for (int i = 0; i < amenitiesList.size(); i++) {
            //Concatenate value
            String amenityId = manage.hotelAmenityManager.createId(amenities[indices.get(i)]);
            hotelAmenities.add(amenityId);
        }

        return hotelAmenities;
    }
}

