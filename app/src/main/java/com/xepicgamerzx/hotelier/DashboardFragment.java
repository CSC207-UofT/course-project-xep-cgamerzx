package com.xepicgamerzx.hotelier;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.customer.HotelViewAdapter;
import com.xepicgamerzx.hotelier.customer.HotelViewModel;
import com.xepicgamerzx.hotelier.customer.SearchActivity;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.RoomManager;
import com.xepicgamerzx.hotelier.user.UserManager;
import com.xepicgamerzx.hotelier.user.model.User;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView nameField;

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

        HotelManager hotelManager = HotelManager.getManager(getActivity().getApplication());
        RoomManager roomManager = RoomManager.getManager(getActivity().getApplication());

        RecyclerView hotelsRecyclerView = v.findViewById(R.id.newListingsView);

        List<Hotel> hotels = hotelManager.getAll();
        List<HotelViewModel> hotelsView = hotelManager.generateHotelModel(hotels);

        final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(hotelsView);
        hotelsRecyclerView.setAdapter(hotelsAdapter);

        UserManager um = new UserManager();

        // Add if empty, no user, go sign in.
        if (um.getUser(getContext()) != null) {
            User user = (User) um.getUser(getContext());
            nameField.setText("Welcome back " + user.getUserName());
        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
        return v;
    }

    public TextView getNameField() {
        View v = getView();
        TextView nameField = v.findViewById(R.id.welcomeField);
        return nameField;
    }
}