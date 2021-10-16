package com.xepicgamerzx.hotelier.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.xepicgamerzx.hotelier.R;

public class activity_hotel_description_rooms extends AppCompatActivity {

    private TextView descNameText;

    HotelViewModel hotelViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_description_rooms);

        descNameText = findViewById(R.id.hotelNameDesc);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            hotelViewModel = (HotelViewModel) intent.getSerializableExtra("Hotel"); // Gives the hotel object
            descNameText.setText(hotelViewModel.getName());
        }
    }
}