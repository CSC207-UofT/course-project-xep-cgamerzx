package com.xepicgamerzx.hotelier;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xepicgamerzx.hotelier.user.LoginActivity;
import com.xepicgamerzx.hotelier.user.RegisterActivity;
import com.xepicgamerzx.hotelier.user.UserManager;

public class ProfileFragment extends Fragment {
    Button registerBtn;
    Button login;
    Button signOut;

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

        UserManager um = new UserManager();

        if (um.getUser(getContext()) != null) {
            login.setVisibility(View.INVISIBLE);
            signOut.setVisibility(View.VISIBLE);
        }



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
            }
        });
        return v;
    }
}