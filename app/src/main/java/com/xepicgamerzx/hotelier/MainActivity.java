package com.xepicgamerzx.hotelier;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.ReadDummyData.ReadDummyData;
import com.xepicgamerzx.hotelier.databinding.ActivityMainBinding;
import com.xepicgamerzx.hotelier.storage.HotelManager;

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

        HotelManager hotelManager = HotelManager.getManager(getApplication());
        // LOADING DUMMY DATA ON FIRST TIME LOADING APP, CAN PROBABLY USE AN API LATER
        if (hotelManager.getAll().isEmpty()) {
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