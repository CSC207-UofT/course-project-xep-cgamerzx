package com.xepicgamerzx.hotelier.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.xepicgamerzx.hotelier.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HotelView extends AppCompatActivity {

    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        getSupportActionBar().hide();

        RecyclerView hotelsRecyclerView = findViewById(R.id.hotelsRecyclerView);
        List<HotelViewModel> hotels = new ArrayList<>();
        backBtn = findViewById(R.id.backBtn);

        // Creating one dummy hotel
        List<BigDecimal> priceRange = new ArrayList<>();
        BigDecimal number = new BigDecimal(123);
        priceRange.add(number);
        priceRange.add(number);

        HotelViewModel hotel = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
        hotels.add(hotel);

        HotelViewModel hotel1 = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
        hotels.add(hotel);

        HotelViewModel hotel2 = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
        hotels.add(hotel);

        HotelViewModel hotel3 = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
        hotels.add(hotel);

        HotelViewModel hotel4 = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
        hotels.add(hotel);

        HotelViewModel hotel5 = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
        hotels.add(hotel);


        final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(hotels);

        hotelsRecyclerView.setAdapter(hotelsAdapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                // pop backstack?
            }
        });


    }
}