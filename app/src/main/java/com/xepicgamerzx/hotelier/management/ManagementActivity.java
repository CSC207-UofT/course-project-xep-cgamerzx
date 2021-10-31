package com.xepicgamerzx.hotelier.management;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.Bed;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;
import com.xepicgamerzx.hotelier.storage.BedManager;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.RoomManager;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ManagementActivity extends AppCompatActivity {
    public ArrayList<Bed> bedsList;
    private final HashMap<String, Address> addressField; // Ask how to use generics? idk about object
    private final HashMap<String, Long> dateRange;
    private final List<HotelRoom> hotelRooms;
    private int countRooms = 0;
    private final BedManager bedManager;
    private final RoomManager roomManager;
    private final HotelManager hotelManager;

    // Android App Fields
    private TextInputEditText nameInput;
    private Button submitButton;
    private Button addAddressButton;
    private Button addRoomsBtn;
    private TextView successText, roomDetailsText;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText streetNum, streetName, city, province, postalCode, longLat;
    private Button saveAddressButton, cancelAddressButton;

    private EditText capacityIn, bedSizeIn, totalBedsIn, priceIn;
    private Button saveRoomBtn, cancelRoomBtn, availabilityBtn, addBedsBtn;
    private TextView dateSelector;

    public ManagementActivity() {
        this.bedManager = BedManager.getManager(getApplication());
        this.roomManager = RoomManager.getManager(getApplication());
        this.hotelManager = HotelManager.getManager(getApplication());

        this.bedsList = new ArrayList<>();
        this.dateRange = new HashMap<>();
        this.addressField = new HashMap<>();
        this.hotelRooms = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        nameInput = findViewById(R.id.inputText);
        submitButton = findViewById(R.id.submit);
        addAddressButton = findViewById(R.id.addAddressBtn);
        successText = findViewById(R.id.successTxt);
        addRoomsBtn = findViewById(R.id.addRoomsBtn);


        // Getting the data.
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String hotelName = nameInput.getText().toString();
                // Address is added to the hashmap when the user adds an address.

                Address address = addressField.get("Address");
                // TODO Implement star class
                hotelManager.createHotel(hotelName, address, 5, (HotelRoom) hotelRooms);

                // Resetting fields.
                nameInput.setText("");
                roomDetailsText.setText("");
                successText.setText("");
                countRooms = 0;

            }
        });

        addAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAddressDialog();
            }
        });

        addRoomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRoomsDialog();
            }
        });

    }

    public void createNewAddressDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View addressPopUpView = getLayoutInflater().inflate(R.layout.address_popup, null);

        streetNum = addressPopUpView.findViewById(R.id.streetNum);
        streetName = addressPopUpView.findViewById(R.id.streetName);
        city = addressPopUpView.findViewById(R.id.city);
        province = addressPopUpView.findViewById(R.id.province);
        postalCode = addressPopUpView.findViewById(R.id.postalCode);
        longLat = addressPopUpView.findViewById(R.id.longAndLat);

        saveAddressButton = addressPopUpView.findViewById(R.id.saveButton);
        cancelAddressButton = addressPopUpView.findViewById(R.id.cancelButton);

        dialogBuilder.setView(addressPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        saveAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hotelStreetNum = streetNum.getText().toString();
                String hotelStreetName = streetName.getText().toString();
                String hotelCity = city.getText().toString();
                String hotelProvince = province.getText().toString();
                String hotelPostalCode = postalCode.getText().toString();
                String hotelLongAndLat = longLat.getText().toString();

                String[] res = hotelLongAndLat.split("[,]", 0);
                double longitude = Double.parseDouble(res[0]);
                double latitude = Double.parseDouble(res[1]);

                // Create address object
                Address address = new Address(hotelStreetName,
                        hotelPostalCode, hotelStreetNum,
                        hotelCity, hotelProvince, longitude,
                        latitude);


                dialog.dismiss();
                successText.setText("Added");

                addressField.put("Address", address);

            }
        });

        cancelAddressButton.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       dialog.dismiss();
                                                   }
                                               }

        );
    }

    public void createRoomsDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View roomsPopUpView = getLayoutInflater().inflate(R.layout.add_rooms_popup, null);

        availabilityBtn = roomsPopUpView.findViewById(R.id.availabilityBtn);
        capacityIn = roomsPopUpView.findViewById(R.id.capacityIn);
        bedSizeIn = roomsPopUpView.findViewById(R.id.bedSizeIn);
        totalBedsIn = roomsPopUpView.findViewById(R.id.numberOfBedsIn);
        addBedsBtn = roomsPopUpView.findViewById(R.id.addBedsBtn);
        priceIn = roomsPopUpView.findViewById(R.id.priceIn);
        cancelRoomBtn = roomsPopUpView.findViewById(R.id.cancelRoomBtn);
        saveRoomBtn = roomsPopUpView.findViewById(R.id.saveRoomBtn);
        dateSelector = roomsPopUpView.findViewById(R.id.dateSelector);

        dialogBuilder.setView(roomsPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        // Functionality


        addBedsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating beds
                String bedSize = bedSizeIn.getText().toString();
                int totalBeds = Integer.parseInt(totalBedsIn.getText().toString());

                // Adding the inputted beds to the instance variable called beds list.
                // Need to add some input checker to see if valid.
                for (int i = 0; i < totalBeds; i++) {
                    bedsList.add(bedManager.createBed(bedSize));
                }
                // Updating fields.
                bedSizeIn.setText("");
                totalBedsIn.setText("");
                addBedsBtn.setText("Add another bed");

//                System.out.println(bedsList);
            }
        });

        // Date (Using code from Customer Activity)
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("SELECT A CHECK IN AND CHECKOUT DATE");
        final MaterialDatePicker materialDatePicker = builder.build();

        availabilityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_RANGE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                // GIVES EPOCH FORMATTED DATES
                // We probably want to use epoch dates in Rooms class too.
                Long startDate = selection.first;
                Long endDate = selection.second;
//                System.out.println(String.format("%d %d", startDate, endDate));

                // Converts to normal date
                UnixEpochDateConverter epoch = new UnixEpochDateConverter();
                String dates = epoch.epochToLocal(startDate, endDate);
                dateSelector.setText(dates);

                dateRange.put("startDate", startDate);
                dateRange.put("endDate", endDate);
            }
        });

        // ON SAVE, CREATE NEW ROOMS OBJECT, GIVE BEDS REFERENCE TO ROOM AFTER CLICK
        saveRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add error handling later, if any fields are blank
                int capacity = Integer.parseInt(capacityIn.getText().toString());
                BigDecimal price = new BigDecimal(priceIn.getText().toString());
                long startDate = dateRange.get("startDate");
                long endDate = dateRange.get("endDate");
                ZoneId zoneId = ZoneId.systemDefault();

                // Create hotelRoom
                HotelRoom hotelRoom = roomManager.createRoom(zoneId, startDate, endDate, capacity, price);

                // Give beds a reference to their hotelRoom
                //bedManager.setRoomForAllBeds(hotelRoom, bedsList);

                // On save, clear bedsList to empty list.
                // bedsList = new ArrayList<Bed>();
                // System.out.println(bedsList);

                countRooms += 1;

                roomDetailsText = findViewById(R.id.roomDetailsText);
                roomDetailsText.append(String.format("\nHotelRoom %d,\n%s", countRooms, hotelRoom));

                // Adding to the hotelRooms list for a single hotel.
                hotelRooms.add(hotelRoom);

                dialog.dismiss();
            }
        });

        cancelRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}