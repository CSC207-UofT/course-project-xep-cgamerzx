package com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer_activities.customer_rooms_activity.CustomerHotelRoomsActivity;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.user.UserManager;

import java.util.HashMap;
import java.util.List;

public class HotelViewAdapter extends RecyclerView.Adapter<HotelViewAdapter.HotelViewHolder> {

    public List<HotelViewModel> hotels;
    long userStartDate;
    long userEndDate;

    public HotelViewAdapter(List<HotelViewModel> hotels) {
        this.hotels = hotels;
    }

    public HotelViewAdapter(List<HotelViewModel> hotels, long userStartDate, long userEndDate) {
        this.hotels = hotels;
        this.userStartDate = userStartDate;
        this.userEndDate = userEndDate;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotelViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.hotel_item_row, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        holder.bindHotel(hotels.get(position));
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    class HotelViewHolder extends RecyclerView.ViewHolder {

        CardView hotelLayout;
        ImageView hotelImg;
        TextView hotelName;
        TextView hotelAddress;
        TextView totalRooms;
        TextView hotelPrice;

        ImageButton favouritesBtn;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelLayout = itemView.findViewById(R.id.layoutHotel);
            hotelImg = itemView.findViewById(R.id.hotelImg);
            hotelName = itemView.findViewById(R.id.hotelRowName);
            hotelAddress = itemView.findViewById(R.id.hotelRowAddress);
            totalRooms = itemView.findViewById(R.id.hotelRowRoomsTotal);
            hotelPrice = itemView.findViewById(R.id.hotelRowPrice);
            favouritesBtn = itemView.findViewById(R.id.favouritesBtn);

            favouritesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HotelierDatabase hotelierDatabase = HotelierDatabase.getDatabase(v.getContext());
                    UserManager userManager = UserManager.getManager(hotelierDatabase);

                    if (userManager.isLoggedIn()) {
                        System.out.println(userManager.getUserFavourites());
                    }
                }
            });
        }

        public void bindHotel(HotelViewModel hotel) {
            hotelName.setText(hotel.getName());
            hotelAddress.setText(hotel.getAddress());
            hotelPrice.setText(hotel.getPriceRange().toString());
            totalRooms.setText(String.valueOf(hotel.getNumberOfRooms()));
            // img .set

            hotelLayout.setOnClickListener(v -> {
                HashMap<String, Object> data = new HashMap<>();
                if (userStartDate != 0 && userEndDate != 0) {
                    data.put("userStartDate", userStartDate);
                    data.put("userEndDate", userEndDate);
                }
                data.put("Hotel", hotel);
                v.getContext().startActivity(new Intent(v.getContext(), CustomerHotelRoomsActivity.class).putExtra("HotelData", data));
            });

            favouritesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HotelierDatabase hotelierDatabase = HotelierDatabase.getDatabase(v.getContext());
                    UserManager userManager = UserManager.getManager(hotelierDatabase);
                    if (userManager.isLoggedIn()){
                        userManager.updateUserFavourites(hotel.getHotel().hotelId);
                    } else {
                        System.out.println("Please Sign In");
                    }
                }
            });

        }
    }
}
