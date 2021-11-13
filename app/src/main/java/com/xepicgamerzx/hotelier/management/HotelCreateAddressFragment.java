package com.xepicgamerzx.hotelier.management;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Address;
import com.xepicgamerzx.hotelier.objects.OldObjects.AddressOld;

public class HotelCreateAddressFragment extends Fragment {

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
        TextInputEditText streetNum = v.findViewById(R.id.streetNumInp);
        TextInputEditText streetName = v.findViewById(R.id.streetNameInp);
        TextInputEditText city = v.findViewById(R.id.cityInp);
        TextInputEditText province = v.findViewById(R.id.provinceInp);
        TextInputEditText postalCode = v.findViewById(R.id.postalCode);
        TextInputEditText longLat = v.findViewById(R.id.longLatInp);

        ImageButton closeBtn = v.findViewById(R.id.closeBtn);
        MaterialButton saveBtn = v.findViewById(R.id.saveAddressBtn);

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
                getActivity().onBackPressed();
            }
        });

        return v;
    }

}