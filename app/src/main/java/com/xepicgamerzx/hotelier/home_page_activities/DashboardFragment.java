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
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;
import com.xepicgamerzx.hotelier.storage.user.model.User;
import com.xepicgamerzx.hotelier.storage.user.UserManager;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class DashboardFragment extends Fragment {

    HotelierDatabase hotelierDatabase;
    TextView nameField, recentSearches, recentSearchesTxt;
    TextInputEditText search;
    Manage manage;
    RecyclerView hotelsRecyclerView;
    UserManager userManager;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        setAllFields(v);
        checkForUser();
        setHomePageHotels();

        return v;
    }

    public void setAllFields(View v) {
        hotelierDatabase = HotelierDatabase.getDatabase(requireActivity().getApplication());
        userManager = UserManager.getManager(hotelierDatabase);
        recentSearches = v.findViewById(R.id.recentSearches);
        search = v.findViewById(R.id.searchToFragment);
        nameField = v.findViewById(R.id.welcomeField);
        hotelsRecyclerView = v.findViewById(R.id.newListingsView);
        recentSearchesTxt = v.findViewById(R.id.recentSearchesTxt);
        search.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), SearchActivity.class)));
    }


    @SuppressLint("SetTextI18n")
    public void checkForUser() {
        User user = userManager.getUser();
        // Add if empty, no user, go sign in.
        if (user != null) {
            nameField.setText("Welcome back " + user.getUserName());
            recentSearches.setVisibility(View.VISIBLE);
            recentSearchesTxt.setVisibility(View.VISIBLE);
            setRecentSearches();
        } else {
            recentSearches.setVisibility(View.GONE);
            recentSearchesTxt.setVisibility(View.GONE);
            nameField.setText("Register today.");
        }
    }

    public void setRecentSearches() {
        List<String> searchesList = userManager.getRecentSearches();
        Collections.reverse(searchesList);
        String searches;
        if (searchesList.size() >= 5) {
            searches = String.join("\n", searchesList.subList(0, 5));
        } else {
            searches = String.join("\n", searchesList);
        }
        recentSearchesTxt.setText(searches);
    }

    public void setHomePageHotels() {
        manage = Manage.getManager(requireActivity().getApplication());
        List<HotelViewModel> hotelsView = manage.hotelManager.generateHotelModel();
        Collections.reverse(hotelsView); // Reversing for "Newest listings"
        final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(hotelsView);
        hotelsRecyclerView.setAdapter(hotelsAdapter);
    }

    public TextView getNameField() {
        View v = getView();
        return Objects.requireNonNull(v).findViewById(R.id.welcomeField);
    }
}