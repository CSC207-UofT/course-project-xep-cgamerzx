package com.xepicgamerzx.hotelier.management_hotel_listing_activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.R;

import java.util.ArrayList;

public class AmenityAdapter extends RecyclerView.Adapter<AmenityAdapter.AmenityViewHolder> {

    ArrayList<String> arrayList;

    public AmenityAdapter(ArrayList<String> arrayList) {
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

    public static class AmenityViewHolder extends RecyclerView.ViewHolder {
        public CheckBox tagText;

        public AmenityViewHolder(View view) {
            super(view);

            tagText = view.findViewById(R.id.CheckBox);
        }
    }
}

