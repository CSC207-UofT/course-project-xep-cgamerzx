package com.xepicgamerzx.hotelier.home_page_activities;

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
import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewAdapterBuilder;
import com.xepicgamerzx.hotelier.customer_activities.customer_search_activity.SearchActivity;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;
import com.xepicgamerzx.hotelier.storage.user.UserManager;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Main dashboard fragment
 */
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

    private void setAllFields(View v) {
        hotelierDatabase = HotelierDatabase.getDatabase(requireActivity().getApplication());
        userManager = UserManager.getManager(hotelierDatabase);
        recentSearches = v.findViewById(R.id.recentSearches);
        search = v.findViewById(R.id.searchToFragment);
        nameField = v.findViewById(R.id.welcomeField);
        hotelsRecyclerView = v.findViewById(R.id.newListingsView);
        recentSearchesTxt = v.findViewById(R.id.recentSearchesTxt);
        search.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), SearchActivity.class)));
    }


    private void checkForUser() {
        String username = userManager.getUserName();
        // Add if empty, no user, go sign in.
        if (username != null) {
            String welcomeMsg = "Welcome back " + username;
            nameField.setText(welcomeMsg);
            recentSearches.setVisibility(View.VISIBLE);
            recentSearchesTxt.setVisibility(View.VISIBLE);
            setRecentSearches();
        } else {
            recentSearches.setVisibility(View.GONE);
            recentSearchesTxt.setVisibility(View.GONE);
            nameField.setText(R.string.register_today);
        }
    }

    private void setRecentSearches() {
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

    private void setHomePageHotels() {
        manage = Manage.getManager(requireActivity().getApplication());
        final HotelViewAdapter hotelsAdapter = new HotelViewAdapterBuilder(
                requireActivity().getApplication())
                .setReverse(true).build();
        hotelsRecyclerView.setAdapter(hotelsAdapter);
    }

    private TextView getNameField() {
        View v = getView();
        return Objects.requireNonNull(v).findViewById(R.id.welcomeField);
    }
}