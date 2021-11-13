package com.xepicgamerzx.hotelier.management;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.OldObjects.AddressOld;

public class HotelCreateAddressFragment extends Fragment {

    TextInputEditText streetNum;
    TextInputEditText streetName;
    TextInputEditText city;
    TextInputEditText province;
    TextInputEditText postalCode;
    TextInputEditText longLat;

    ImageButton closeBtn;
    MaterialButton saveBtn;

    public HotelCreateAddressFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hotel_create_address, container, false);

        // Address fields
        streetNum = v.findViewById(R.id.streetNumInp);
        streetName = v.findViewById(R.id.streetNameInp);
        city = v.findViewById(R.id.cityInp);
        province = v.findViewById(R.id.provinceInp);
        postalCode = v.findViewById(R.id.postalCode);
        longLat = v.findViewById(R.id.longLatInp);

        closeBtn = v.findViewById(R.id.closeBtn);
        saveBtn = v.findViewById(R.id.saveAddressBtn);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotelCreatorActivity activity = (HotelCreatorActivity) getActivity();
                String validatedMessage = validateInput();

                if (!validatedMessage.equals("")) {
                    Toast.makeText(getContext(), validatedMessage, Toast.LENGTH_SHORT).show();
                } else {
                    activity.address = new Address(
                            streetName.getText().toString(),
                            postalCode.getText().toString(),
                            streetNum.getText().toString(),
                            city.getText().toString(),
                            province.getText().toString(),
                            Double.parseDouble(longLat.getText().toString().split(",")[0]),
                            Double.parseDouble(longLat.getText().toString().split(",")[1])
                    );
                    System.out.println(activity.address);

                    activity.text += "\n" + activity.address.toString();
                    activity.isAddressMade = true;

                    // Deactivate button
                    activity.addAddressBtn.setOnClickListener(null);
                    activity.addAddressBtn.setText("Success");

                    getActivity().onBackPressed();
                }
            }
        });

        return v;
    }

    public String validateInput() {
        String s = "";
        if( !(streetNum.getText().toString().matches("\\d+")) ||
                postalCode.getText().toString().equals("") ||
                streetName.getText().toString().equals("") ||
                city.getText().toString().equals("") ||
                province.getText().toString().equals("") ||
                !(longLat.getText().toString().matches(
                        "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$"
                ))) {
            s = "Enter all fields in the specified format.";
        }

        return s;
    }

}