package com.xepicgamerzx.hotelier.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.RoomManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HotelViewActivity extends AppCompatActivity {

    ImageButton backBtn;
    HotelManager hotelManager;
    RoomManager roomManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        getSupportActionBar().hide();

        hotelManager = HotelManager.getManager(getApplication());
        roomManager = RoomManager.getManager(getApplication());

        RecyclerView hotelsRecyclerView = findViewById(R.id.hotelsRecyclerView);

        List<Hotel> hotels = hotelManager.getAll();
        List<HotelViewModel> hotelsView = new ArrayList<>();
        // creating view model
        for (Hotel hotel : hotels) {
            hotelsView.add(new HotelViewModel(
                    hotel.getName(),
                    hotel.getAddress().getFullStreet(),
                    roomManager.getPriceRange(hotel),
                    roomManager.getNumberOfRooms(hotel),
                    hotel
            ));
            System.out.println(hotel.getName());
        }
        backBtn = findViewById(R.id.backBtn);

        // Creating one dummy hotel
//        List<BigDecimal> priceRange = new ArrayList<>();
//        BigDecimal number = new BigDecimal(123);
//        priceRange.add(number);
//        priceRange.add(number);
//
//        HotelViewModel hotel = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
//        hotels.add(hotel);
//
//        HotelViewModel hotel1 = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
//        hotels.add(hotel);
//
//        HotelViewModel hotel2 = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
//        hotels.add(hotel);
//
//        HotelViewModel hotel3 = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
//        hotels.add(hotel);
//
//        HotelViewModel hotel4 = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
//        hotels.add(hotel);
//
//        HotelViewModel hotel5 = new HotelViewModel("DoubleTree", "123 Avenue", priceRange, 3);
//        hotels.add(hotel);

        final HotelViewAdapter hotelsAdapter = new HotelViewAdapter(hotelsView);

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