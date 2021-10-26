package com.xepicgamerzx.hotelier.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer.CustomerActivity;
import com.xepicgamerzx.hotelier.databinding.ActivityMainBinding;
import com.xepicgamerzx.hotelier.management.ManagementActivity;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelManager;

import java.util.List;

public class new_home_page extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private HotelManager hotelManager = new HotelManager();

    private Button managementBtn;
    private Button customerBtn;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home_page);


        managementBtn = (Button) findViewById(R.id.managementBtn);
        customerBtn = (Button) findViewById(R.id.customerBtn);
        resetBtn = (Button) findViewById(R.id.resetDataBtn);

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

        resetBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetData();
            loadData();
        }
    });

}

    /**
     * Called when user taps the Management button to open the management page
     *
     * @param v starting view
     */
    public void openManagement(View v) {
        Intent intent = new Intent(new_home_page.this, ManagementActivity.class).putExtra("HotelManager", hotelManager);
        startActivity(intent);
    }

    /**
     * Called when user taps the Customer button to open the customer page.
     *
     * @param view starting view
     */
    public void openCustomer(View view) {
        Intent intent = new Intent(this, CustomerActivity.class).putExtra("HotelManager", hotelManager);
        ;
        startActivity(intent);
    }

    public void loadData() {
        List<Hotel> test = hotelManager.loadHotels(binding.getRoot().getContext());

        hotelManager.loadHotels(binding.getRoot().getContext());

    }

    public void resetData() {
        hotelManager.resetHotelsList();
        hotelManager.saveData(binding.getRoot().getContext());
    }

}