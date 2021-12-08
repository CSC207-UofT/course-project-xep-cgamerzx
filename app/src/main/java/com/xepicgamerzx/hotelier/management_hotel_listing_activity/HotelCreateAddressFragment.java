package com.xepicgamerzx.hotelier.management_hotel_listing_activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;

import java.util.Objects;

public class HotelCreateAddressFragment extends Fragment {

    TextInputEditText streetNum, streetName, city, province, postalCode, longLat;
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
        setAddressFields(v);

        saveBtn.setOnClickListener(v12 -> {
            String validatedMessage = validateInput();
            validAddressBuilder(validatedMessage);
        });

        closeBtn.setOnClickListener(v1 -> requireActivity().onBackPressed());

        return v;
    }

    public void validAddressBuilder(String validatedMessage) {
        HotelCreatorActivity activity = (HotelCreatorActivity) getActivity();

        if (!validatedMessage.equals("")) {
            Toast.makeText(getContext(), validatedMessage, Toast.LENGTH_SHORT).show();
        } else {
            Objects.requireNonNull(activity);

            activity.viewModel.setStreetName(Objects.requireNonNull(streetName.getText()).toString());
            activity.viewModel.setPostalCode(Objects.requireNonNull(postalCode.getText()).toString());
            activity.viewModel.setStreetNumber(Objects.requireNonNull(streetNum.getText()).toString());
            activity.viewModel.setCity(Objects.requireNonNull(city.getText()).toString());
            activity.viewModel.setProvince(Objects.requireNonNull(province.getText()).toString());
            activity.viewModel.setLatitude(Double.parseDouble(Objects.requireNonNull(longLat.getText()).toString().split(",")[0]));
            activity.viewModel.setLongitude(Double.parseDouble(longLat.getText().toString().split(",")[1]));

            activity.text += "\n" + activity.viewModel.addressToString();
            activity.isAddressMade = true;

            // Deactivate button
            activity.addAddressBtn.setOnClickListener(null);
            activity.addAddressBtn.setText("Success");

            requireActivity().onBackPressed();
        }
    }

    public void setAddressFields(View v) {
        streetNum = v.findViewById(R.id.streetNumInp);
        streetName = v.findViewById(R.id.streetNameInp);
        city = v.findViewById(R.id.cityInp);
        province = v.findViewById(R.id.provinceInp);
        postalCode = v.findViewById(R.id.postalCode);
        longLat = v.findViewById(R.id.longLatInp);
        closeBtn = v.findViewById(R.id.closeBtn);
        saveBtn = v.findViewById(R.id.saveAddressBtn);
    }

    public String validateInput() {
        String s = "";
        if (!Objects.requireNonNull(streetNum.getText()).toString().matches("\\d+") ||
                Objects.requireNonNull(postalCode.getText()).toString().equals("") ||
                Objects.requireNonNull(streetName.getText()).toString().equals("") ||
                Objects.requireNonNull(city.getText()).toString().equals("") ||
                Objects.requireNonNull(province.getText()).toString().equals("") ||
                !Objects.requireNonNull(longLat.getText()).toString().matches(
                        "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$"
                )) {
            s = "Enter all fields in the specified format.";
        }
        return s;
    }

}