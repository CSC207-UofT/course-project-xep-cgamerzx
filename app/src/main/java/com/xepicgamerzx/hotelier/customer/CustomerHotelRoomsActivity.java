package com.xepicgamerzx.hotelier.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Hotel;

public class CustomerHotelRoomsActivity extends AppCompatActivity {

    HotelViewModel hotelViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rooms);
        getSupportActionBar().hide();

        TextView descNameText = findViewById(R.id.hotelNameDesc);
        TextView hotelAddress = findViewById(R.id.addressTxt);
        TextView hotelRating = findViewById(R.id.ratingTxt);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            hotelViewModel = (HotelViewModel) intent.getSerializableExtra("Hotel"); // Gives the hotel object
            Hotel hotel = hotelViewModel.getHotel();

            descNameText.setText(hotelViewModel.getHotel().getName());
            hotelAddress.setText("Address: " + hotel.getAddress().getFullStreet());
            hotelRating.setText("Rating: " + String.valueOf(hotel.getStarClass()) + " Stars");

            double latitude = hotel.getAddress().getLatitude();
            double longitude = hotel.getAddress().getLongitude();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            MapsFragment fragInfo = new MapsFragment();

            Bundle bundle = new Bundle();
            bundle.putDouble("longitude", longitude);
            bundle.putDouble("latitude", latitude);
            fragInfo.setArguments(bundle);

            fragmentTransaction.replace(R.id.frameMapsLay, fragInfo).commit();

        }
    }
}