package com.xepicgamerzx.hotelier.home_page_activities;

import android.os.Bundle;
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

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BottomNavigationView navView;

    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HotelierDatabase hotelierDatabase = HotelierDatabase.getDatabase(getApplication());
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

        navView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);

    }
}