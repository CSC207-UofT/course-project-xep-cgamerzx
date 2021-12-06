package com.xepicgamerzx.hotelier.home_page_activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewAdapter;
import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewModel;
import com.xepicgamerzx.hotelier.customer_activities.customer_search_activity.SearchActivity;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelManager;
import com.xepicgamerzx.hotelier.storage.user.model.User;
import com.xepicgamerzx.hotelier.storage.user.UserManager;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class DashboardFragment extends Fragment {

    HotelierDatabase hotelierDatabase;
    TextView nameField, recentSearches;
    TextInputEditText search;
    Manage manage;
    RecyclerView hotelsRecyclerView;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        hotelierDatabase = HotelierDatabase.getDatabase(requireActivity().getApplication());
        recentSearches = v.findViewById(R.id.recentSearches);
        search = v.findViewById(R.id.searchToFragment);
        nameField = v.findViewById(R.id.welcomeField);
        hotelsRecyclerView = v.findViewById(R.id.newListingsView);
        search.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), SearchActivity.class)));

        checkForUser();
        setHomePageHotels();

        return v;
    }


    @SuppressLint("SetTextI18n")
    public void checkForUser() {
        UserManager userManager = UserManager.getManager(hotelierDatabase);
        User user = userManager.getUser();

        // Add if empty, no user, go sign in.
        if (user != null) {
            nameField.setText("Welcome back " + user.getUserName());
            recentSearches.setVisibility(View.VISIBLE);
        } else {
            recentSearches.setVisibility(View.GONE);
            nameField.setText("Register today.");
        }
    }

    public void setHomePageHotels() {
        manage = Manage.getManager(requireActivity().getApplication());
        List<Hotel> hotels = manage.hotelManager.getAllHotels();
        List<HotelViewModel> hotelsView = manage.hotelManager.generateHotelModel(hotels);
        Collections.reverse(hotelsView); // Reversing for "Newest listings"
        final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(hotelsView);
        hotelsRecyclerView.setAdapter(hotelsAdapter);
    }

    public TextView getNameField() {
        View v = getView();
        return Objects.requireNonNull(v).findViewById(R.id.welcomeField);
    }
}