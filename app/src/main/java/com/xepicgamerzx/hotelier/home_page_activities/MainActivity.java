package com.xepicgamerzx.hotelier.home_page_activities;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.databinding.ActivityMainBinding;
import com.xepicgamerzx.hotelier.read_dummy_data.ReadDummyData;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.user_activities.UserManager;

import org.json.JSONException;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    HotelierDatabase hotelierDatabase;
    private ActivityMainBinding binding;
    private BottomNavigationView navView;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hotelierDatabase = HotelierDatabase.getDatabase(getApplicationContext());
        loadData(); // Loading data on the first app launch.
        setRecentLogin();

        // Setting up navigation view.
        navView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        NavigationUI.setupWithNavController(navView, navController);


    }

    public void setRecentLogin() {
        hotelierDatabase = HotelierDatabase.getDatabase(getApplication());
        UserManager um = UserManager.getManager(hotelierDatabase);
        um.setLastLoggedInUser();
    }

    public void loadData() {
        // LOADING DUMMY DATA ON FIRST TIME LOADING APP, CAN PROBABLY USE AN API LATER
        if (hotelierDatabase.hotelDao().getAll().isEmpty()) {
            ReadDummyData readDummyData = new ReadDummyData(getApplication());
            try {
                readDummyData.readData(getApplicationContext());
                System.out.println("Success");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}