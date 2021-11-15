package com.xepicgamerzx.hotelier.customer;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.MainActivity;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.storage.RoomBedsCrossManager;

import java.util.ArrayList;
import java.util.List;

public class CustomerHotelRoomsAdapter extends RecyclerView.Adapter<CustomerHotelRoomsAdapter.CustomerHotelRoomsHolder> {

    public List<CustomerHotelRoomsModel> hotelRooms;

    public CustomerHotelRoomsAdapter(List<CustomerHotelRoomsModel> hotelRooms) {
        this.hotelRooms = hotelRooms;
    }

    @NonNull
    @Override
    public CustomerHotelRoomsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerHotelRoomsHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.customer_rooms_list, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHotelRoomsHolder holder, int position) {
        holder.bindRoom(hotelRooms.get(position));
    }

    @Override
    public int getItemCount() {return hotelRooms.size();
    }

    class CustomerHotelRoomsHolder extends RecyclerView.ViewHolder {

        CardView hotelRoomsLayout;
        TextView bedsInRoom;
        TextView bedSizeInRoom;
        TextView hotelRoomCapacity;
        TextView hotelRoomPrice;

        public CustomerHotelRoomsHolder(@NonNull View itemView) {
            super(itemView);
            hotelRoomsLayout = itemView.findViewById(R.id.layoutRooms);
            //bedsInRoom = itemView.findViewById(R.id.roomBeds);
            bedSizeInRoom = itemView.findViewById(R.id.roomBedSize);
            hotelRoomCapacity = itemView.findViewById(R.id.roomCapacity);
            hotelRoomPrice = itemView.findViewById(R.id.roomPrice);
        }

        public void bindRoom(CustomerHotelRoomsModel hotelRoom) {
            //bedsInRoom.setText(hotelRoom.getBedsInRoomCount());
            bedSizeInRoom.setText((CharSequence) hotelRoom.getRelated());
            hotelRoomCapacity.setText(hotelRoom.getCapacity());
            hotelRoomPrice.setText(String.valueOf(hotelRoom.getPrice()));


            hotelRoomsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("hi");
                    v.getContext().startActivity(new Intent(v.getContext(), CustomerHotelRoomsActivity.class).putExtra("Hotel Room", hotelRoom));
                }
            });

        }
    }
}
