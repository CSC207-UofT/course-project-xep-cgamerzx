package com.xepicgamerzx.hotelier.management_hotel_listing_activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelCreateAmenitiesFragment extends Fragment {

    List<String> amenitiesList = Arrays.asList("Indoor Pool", "Outdoor Pool", "Gym", "Laundry",
            "Business Services", "Wedding Services", "Conference Space", "Smoke Free Property",
            "Bar", "Complementary Breakfast", "24/7 Front Desk", "Parking Included", "Restaurant",
            "Spa", "Elevator", "ATM/Banking Services", "Front Desk Safe");


    public HotelCreateAmenitiesFragment() {
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
        View v = inflater.inflate(R.layout.fragment_hotel_create_amenities, container, false);

        //AmenityAdapter adapter = new AmenityAdapter((ArrayList<String>) amenitiesList);

        //RecyclerView listView = v.findViewById(R.id.newListingsView);
        //listView.setAdapter(adapter);

        return v;
    }
}
