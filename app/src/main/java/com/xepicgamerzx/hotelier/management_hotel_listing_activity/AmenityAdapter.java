package com.xepicgamerzx.hotelier.management_hotel_listing_activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer_activities.customer_rooms_activity.CustomerHotelRoomsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AmenityAdapter extends RecyclerView.Adapter<AmenityAdapter.AmenityViewHolder> {

    ArrayList<String> arrayList;

    public AmenityAdapter(ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AmenityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.amenity_item_row, parent, false);

        return new AmenityViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final AmenityViewHolder holder, final int position) {
        holder.tagText.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AmenityViewHolder extends RecyclerView.ViewHolder {
        public CheckBox tagText;

        public AmenityViewHolder(View view) {
            super(view);

            tagText = view.findViewById(R.id.CheckBox);
        }
    }
}

