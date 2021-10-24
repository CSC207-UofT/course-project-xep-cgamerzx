package com.xepicgamerzx.hotelier.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xepicgamerzx.hotelier.R;

public class CustomerHotelRoomsActivity extends AppCompatActivity {

    HotelViewModel hotelViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rooms);

        TextView descNameText = findViewById(R.id.hotelNameDesc);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            hotelViewModel = (HotelViewModel) intent.getSerializableExtra("Hotel"); // Gives the hotel object
            descNameText.setText(hotelViewModel.getName());
        }
    }
}