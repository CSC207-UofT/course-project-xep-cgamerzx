package com.xepicgamerzx.hotelier;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.customer.SearchFragment;
import com.xepicgamerzx.hotelier.databinding.ActivityMainBinding;
import com.xepicgamerzx.hotelier.management.ManagementActivity;
import com.xepicgamerzx.hotelier.user.UserManager;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private TextInputEditText searchInput;
    private BottomNavigationView navView;

    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        navView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);

//        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
//            @Override
//            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
//                if(destination.getId() == R.id.) {
//                    navView.setVisibility(View.GONE);
//                } else {
//                    navView.setVisibility(View.VISIBLE);
//                }
//            }
//        });

    }

    public void replaceFragment(Fragment someFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.dashboardFragment, someFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("Test") // name can be null
                .commit();
    }

    public void openUserSignIn() {

    }

}