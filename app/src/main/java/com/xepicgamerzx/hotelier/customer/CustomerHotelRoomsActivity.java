package com.xepicgamerzx.hotelier.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.BedManager;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.RoomBedsCrossManager;
import com.xepicgamerzx.hotelier.storage.RoomManager;

import java.util.ArrayList;
import java.util.List;

public class CustomerHotelRoomsActivity extends AppCompatActivity {

    HotelViewModel hotelViewModel;
    HotelManager hotelManager;
    RoomManager roomManager;
    BedManager bedManager;
    RoomBedsCrossManager roomBedsCrossManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rooms);
        getSupportActionBar().hide();

        hotelManager = HotelManager.getManager(getApplication());
        roomManager = RoomManager.getManager(getApplication());

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

        hotelManager = HotelManager.getManager(getApplication());
        roomManager = RoomManager.getManager(getApplication());
        bedManager = BedManager.getManager(getApplication());
        roomBedsCrossManager = RoomBedsCrossManager.getManager(getApplication());

        RecyclerView roomsRecyclerView = findViewById(R.id.roomsRecyclerView);

        List<HotelRoom> hotelRooms = roomManager.getAll();
        List<CustomerHotelRoomsModel> hotelRoomsView = new ArrayList<>();
        // View model
        for (HotelRoom hotelRoom : hotelRooms) {
            hotelRoomsView.add(new CustomerHotelRoomsModel(
                    //hotelRoom.getBedsInRoomCount(hotelRoom),
                    roomBedsCrossManager.getRelated(hotelRoom),
                    hotelRoom.getCapacity(),
                    hotelRoom.getPrice(),

                    hotelRoom
            ));
        }
        final CustomerHotelRoomsAdapter hotelRoomsAdapter = new CustomerHotelRoomsAdapter(hotelRoomsView);
        roomsRecyclerView.setAdapter(hotelRoomsAdapter);


    }
}