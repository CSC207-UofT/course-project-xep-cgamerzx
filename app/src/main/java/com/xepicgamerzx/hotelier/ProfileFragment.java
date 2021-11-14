package com.xepicgamerzx.hotelier;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xepicgamerzx.hotelier.management.HotelCreatorActivity;
import com.xepicgamerzx.hotelier.objects.OldObjects.HotelOld;
import com.xepicgamerzx.hotelier.objects.OldObjects.OldHotelManager;
import com.xepicgamerzx.hotelier.storage.FileReadWrite;
import com.xepicgamerzx.hotelier.storage.firebase.HotelOldDAO;
import com.xepicgamerzx.hotelier.storage.firebase.MyCallback;
import com.xepicgamerzx.hotelier.user.LoginActivity;
import com.xepicgamerzx.hotelier.user.RegisterActivity;
import com.xepicgamerzx.hotelier.user.UserManager;
import com.xepicgamerzx.hotelier.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    Button registerBtn;
    Button login;
    Button signOut;
    RelativeLayout signedInContent;
    TextView userNameTxt;
    Button listHotel;


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
        registerBtn = v.findViewById(R.id.toRegisterActivBtn);
        login = v.findViewById(R.id.toLoginActivBtn);

        signOut = v.findViewById(R.id.signOutBtn);
        signOut.setVisibility(View.INVISIBLE);

        signedInContent = v.findViewById(R.id.signedInContent);
        userNameTxt = v.findViewById(R.id.userIdTxt);

        listHotel = v.findViewById(R.id.listHotelBtn);

        UserManager um = new UserManager();
        // causes an error when no user, not fatal
        User user = um.getUser(getContext());

        // If a user is signed in ...
        if (user != null) {
            login.setVisibility(View.INVISIBLE);
            signOut.setVisibility(View.VISIBLE);

            signedInContent.setVisibility(View.VISIBLE);
            userNameTxt.setText(user.getUserName());
        }


        listHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HotelCreatorActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegisterActivity.class));
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                um.signOut(getContext());
                login.setVisibility(View.VISIBLE);
                signOut.setVisibility(View.INVISIBLE);
                signedInContent.setVisibility(View.GONE);
            }
        });


        return v;
    }
}