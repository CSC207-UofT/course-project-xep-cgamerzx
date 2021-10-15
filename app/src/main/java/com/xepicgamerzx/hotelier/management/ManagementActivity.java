package com.xepicgamerzx.hotelier.management;


import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Address;

import java.util.HashMap;


public class ManagementActivity extends AppCompatActivity {
    private HashMap<String, Object> hotelFields; // Ask how to use generics? idk about object

    // Android App Fields
    private TextInputEditText nameInput;
    private Button submitButton;
    private Button addAddressButton;
    private TextView successText;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText streetNum, streetName, city, province, postalCode, longLat;
    private Button saveButton, cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        nameInput = (TextInputEditText) findViewById(R.id.inputText);
        submitButton = (Button) findViewById(R.id.submit);
        addAddressButton = (Button) findViewById(R.id.addAddressBtn);
        successText = (TextView) findViewById(R.id.successTxt);

        // Getting the data.
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String hotelName = nameInput.getText().toString();
                // System.out.println(hotelName);

            }
        });

        addAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAddressDialog();
            }
        });
    }

    public void createNewAddressDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View addressPopUpView = getLayoutInflater().inflate(R.layout.address_popup, null);

        streetNum = (EditText) addressPopUpView.findViewById(R.id.streetNum);
        streetName = (EditText) addressPopUpView.findViewById(R.id.streetName);
        city = (EditText) addressPopUpView.findViewById(R.id.city);
        province = (EditText) addressPopUpView.findViewById(R.id.province);
        postalCode = (EditText) addressPopUpView.findViewById(R.id.postalCode);
        longLat = (EditText) addressPopUpView.findViewById(R.id.longAndLat);

        saveButton = (Button) addressPopUpView.findViewById(R.id.saveButton);
        cancelButton = (Button) addressPopUpView.findViewById(R.id.cancelButton);

        dialogBuilder.setView(addressPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String hotelStreetNum= streetNum.getText().toString();
                  String hotelStreetName= streetName.getText().toString();
                  String hotelCity= city.getText().toString();
                  String hotelProvince = province.getText().toString();
                  String hotelPostalCode = postalCode.getText().toString();
                  String hotelLongAndLat= longLat.getText().toString();

                  String[] res = hotelLongAndLat.split("[,]", 0);
                  double longitude = Double.parseDouble(res[0]);
                  double latitude = Double.parseDouble(res[1]);

                  // Create address object
                  Address address = new Address(hotelStreetName,
                          hotelPostalCode, hotelStreetNum,
                          hotelCity, hotelProvince, longitude,
                          latitude);

                  System.out.println(address.toString());

                  dialog.dismiss();
                  successText.setText("Added");

                  hotelFields.put("Address", address);

                  System.out.println("Success");


              }
          }

        );

        cancelButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  dialog.dismiss();
              }
          }

        );
    }

}