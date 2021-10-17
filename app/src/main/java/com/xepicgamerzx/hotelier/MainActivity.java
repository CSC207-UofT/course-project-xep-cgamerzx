package com.xepicgamerzx.hotelier;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.xepicgamerzx.hotelier.customer.CustomerActivity;
import com.xepicgamerzx.hotelier.databinding.ActivityMainBinding;
import com.xepicgamerzx.hotelier.management.ManagementActivity;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.IHotelManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private HotelManager hotelManager = new HotelManager();

    private Button managementBtn;
    private Button customerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        managementBtn = (Button) findViewById(R.id.managementBtn);
        customerBtn = (Button) findViewById(R.id.customerBtn);

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
        Intent intent = new Intent(MainActivity.this, ManagementActivity.class).putExtra("HotelManager", hotelManager);
        startActivity(intent);
    }

    /**
     * Called when user taps the Customer button to open the customer page.
     *
     * @param view starting view
     */
    public void openCustomer(View view) {
        Intent intent = new Intent(this, CustomerActivity.class).putExtra("HotelManager", hotelManager);;
        startActivity(intent);
    }

    public void loadData() {
        List<Hotel> test = hotelManager.loadHotels(binding.getRoot().getContext());

        hotelManager.loadHotels(binding.getRoot().getContext());

        // was resetting hotels.
        //        hotelManager.getAllHotels().remove(0);
        //        System.out.println(hotelManager.getAllHotels());
        //        hotelManager.saveData(binding.getRoot().getContext());
    }
}