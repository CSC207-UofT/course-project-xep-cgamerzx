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
import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewModel;
import com.xepicgamerzx.hotelier.customer_activities.customer_search_activity.SearchActivity;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;
import com.xepicgamerzx.hotelier.storage.user.model.User;
import com.xepicgamerzx.hotelier.user_activities.UserManager;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView nameField;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        TextInputEditText search = v.findViewById(R.id.searchToFragment);
        nameField = v.findViewById(R.id.welcomeField);

        Manage manage = Manage.getManager(requireActivity().getApplication());
        HotelierDatabase hotelierDatabase = HotelierDatabase.getDatabase(requireActivity().getApplication());

        RecyclerView hotelsRecyclerView = v.findViewById(R.id.newListingsView);

        List<Hotel> hotels = hotelierDatabase.hotelDao().getAll();
        List<HotelViewModel> hotelsView = manage.hotelManager.generateHotelModel(hotels);
        Collections.reverse(hotelsView); // Reversing for "Newest listings"

        final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(hotelsView);
        hotelsRecyclerView.setAdapter(hotelsAdapter);

        // UserManager um = UserManager.getManager(requireActivity().getApplication());
        HotelierDatabase hotelierDb = HotelierDatabase.getDatabase(getContext());
        UserManager userManager = UserManager.getManager(hotelierDb);
        User user = userManager.user;

        // Add if empty, no user, go sign in.
        if (user != null) {
            nameField.setText("Welcome back " + user.getUserName());
        }


        search.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), SearchActivity.class)));
        return v;
    }

    public TextView getNameField() {
        View v = getView();
        return Objects.requireNonNull(v).findViewById(R.id.welcomeField);
    }
}