package com.xepicgamerzx.hotelier.customer;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.MainActivity;
import com.xepicgamerzx.hotelier.R;

import java.util.ArrayList;
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

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelLayout = itemView.findViewById(R.id.layoutHotel);
            hotelImg = itemView.findViewById(R.id.hotelImg);
            hotelName = itemView.findViewById(R.id.hotelRowName);
            hotelAddress = itemView.findViewById(R.id.hotelRowAddress);
            totalRooms = itemView.findViewById(R.id.hotelRowRoomsTotal);
            hotelPrice = itemView.findViewById(R.id.hotelRowPrice);
        }

        public void bindHotel(HotelViewModel hotel) {
            hotelName.setText(hotel.getName());
            hotelAddress.setText(hotel.getAddress());
            hotelPrice.setText(hotel.getPriceRange().toString());
            totalRooms.setText(String.valueOf(hotel.getNumberOfRooms()));
            // img .set

            hotelLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap<String, Object> data = new HashMap<>();
                    if(userStartDate != 0 && userEndDate != 0) {
                        data.put("startDate", userStartDate);
                        data.put("endDate", userEndDate);
                    }
                    data.put("Hotel", hotel);
                    v.getContext().startActivity(new Intent(v.getContext(), CustomerHotelRoomsActivity.class).putExtra("HotelData", data));
                }
            });

        }
    }
}
