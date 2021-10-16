package com.xepicgamerzx.hotelier;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.xepicgamerzx.hotelier.customer.CustomerActivity;
import com.xepicgamerzx.hotelier.databinding.ActivityMainBinding;
import com.xepicgamerzx.hotelier.management.ManagementActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * Called when user taps the Management button to open the management page
     *
     * @param view starting view
     */
    public void openManagement(View view) {
        Intent intent = new Intent(this, ManagementActivity.class);
        startActivity(intent);
    }

    /**
     * Called when user taps the Customer button to open the customer page.
     *
     * @param view starting view
     */
    public void openCustomer(View view) {
        Intent intent = new Intent(this, CustomerActivity.class);
        startActivity(intent);
    }
}