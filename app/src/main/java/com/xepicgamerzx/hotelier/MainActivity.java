package com.xepicgamerzx.hotelier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.xepicgamerzx.hotelier.customer.CustomerActivity;
import com.xepicgamerzx.hotelier.databinding.ActivityMainBinding;
import com.xepicgamerzx.hotelier.management.ManagementActivity;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private HotelManager hotelManager;

    private Button managementBtn;
    private Button customerBtn;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        managementBtn = (Button) findViewById(R.id.managementBtn);
        customerBtn = (Button) findViewById(R.id.customerBtn);
        resetBtn = (Button) findViewById(R.id.resetDataBtn);

        hotelManager = new HotelManager(getApplication());

        managementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                openManagement(v);
            }
        });

        customerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                openCustomer(v);
            }
        });

    }

    /**
     * Called when user taps the Management button to open the management page
     *
     * @param v starting view
     */
    public void openManagement(View v) {
        Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
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

    public void loadData() {
    }

}