package com.xepicgamerzx.hotelier;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.xepicgamerzx.hotelier.databinding.ActivityMainBinding;
import com.xepicgamerzx.hotelier.management.ManagementActivity;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private HotelManager hotelManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



//         managementBtn = (Button) findViewById(R.id.managementBtn);
//         customerBtn = (Button) findViewById(R.id.customerBtn);
//         resetBtn = (Button) findViewById(R.id.resetDataBtn);

//         hotelManager =  HotelManager.getManager(getApplication());

//         managementBtn.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 loadData();
//                 openManagement(v);
//             }
//         });

//         customerBtn.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 loadData();
//                 openCustomer(v);
//             }
//         });

//     }

//     /**
//      * Called when user taps the Management button to open the management page
//      *
//      * @param v starting view
//      */
//     public void openManagement(View v) {
//         Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
//         startActivity(intent);
//     }

//     /**
//      * Called when user taps the Customer button to open the customer page.
//      *
//      * @param view starting view
//      */
//     public void openCustomer(View view) {
//         Intent intent = new Intent(this, CustomerActivity.class);

//         startActivity(intent);
//     }

//     public void loadData() {
//     }

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);

    }

    public void openUserSignIn() {

    }

}