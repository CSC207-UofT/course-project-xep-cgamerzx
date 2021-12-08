package com.xepicgamerzx.hotelier.home_page_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.management_hotel_listing_activity.HotelCreatorActivity;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.user.UserManager;
import com.xepicgamerzx.hotelier.user_activities.LoginActivity;
import com.xepicgamerzx.hotelier.user_activities.RegisterActivity;

/**
 * Fragment for user profile
 */
public class ProfileFragment extends Fragment {
    Button registerBtn;
    Button login;
    Button signOut;
    RelativeLayout signedInContent;
    TextView userNameTxt, signedOutTxt;
    Button listHotel;
    UserManager um = UserManager.getManager(HotelierDatabase.getDatabase(getContext()));

    public ProfileFragment() {
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        setAllFields(v);
        callAllListeners();

        return v;
    }

    private void setAllFields(View v) {
        registerBtn = v.findViewById(R.id.toRegisterActivBtn);
        login = v.findViewById(R.id.toLoginActivBtn);
        signOut = v.findViewById(R.id.signOutBtn);
        signOut.setVisibility(View.INVISIBLE);
        signedInContent = v.findViewById(R.id.signedInContent);
        userNameTxt = v.findViewById(R.id.userIdTxt);
        listHotel = v.findViewById(R.id.listHotelBtn);
        signedOutTxt = v.findViewById(R.id.isSignedIn);
    }

    private void callAllListeners() {
        loginListener();
        registerClickListener();
        signOutClickListener();
        setProfileVisibility();
        listHotel.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), HotelCreatorActivity.class)));
    }

    private void loginListener() {
        login.setOnClickListener(v -> startActivity(new Intent(getActivity(), LoginActivity.class)));
    }

    private void registerClickListener() {
        registerBtn.setOnClickListener(v -> startActivity(new Intent(getActivity(), RegisterActivity.class)));
    }

    private void signOutClickListener() {
        signOut.setOnClickListener(v -> {
            um.signOut(getContext());
            setProfileVisibility();
        });
    }

    private void setProfileVisibility() {
        String username = um.getUserName();

        // If a user is signed in ...
        if (username != null) {
            login.setVisibility(View.INVISIBLE);
            signOut.setVisibility(View.VISIBLE);
            signedInContent.setVisibility(View.VISIBLE);
            userNameTxt.setText(username);
        } else {
            login.setVisibility(View.VISIBLE);
            signOut.setVisibility(View.INVISIBLE);
            signedInContent.setVisibility(View.GONE);
            signedOutTxt.setVisibility(View.VISIBLE);

        }
    }
}