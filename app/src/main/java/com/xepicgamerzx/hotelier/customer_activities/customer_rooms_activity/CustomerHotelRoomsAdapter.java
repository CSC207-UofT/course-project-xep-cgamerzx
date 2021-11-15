package com.xepicgamerzx.hotelier.customer_activities.customer_rooms_activity;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.R;

import java.util.List;

public class CustomerHotelRoomsAdapter extends RecyclerView.Adapter<CustomerHotelRoomsAdapter.CustomerHotelRoomsHolder> {

    public List<CustomerHotelRoomsModel> hotelRoomsModel;

    public CustomerHotelRoomsAdapter(List<CustomerHotelRoomsModel> hotelRoomsModel) {
        this.hotelRoomsModel = hotelRoomsModel;
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
        holder.bindRoom(hotelRoomsModel.get(position));
    }

    @Override
    public int getItemCount() {return hotelRoomsModel.size();
    }

    class CustomerHotelRoomsHolder extends RecyclerView.ViewHolder {

        CardView hotelRoomsLayout;
        TextView bedsInRoom;
        TextView bedSizesInRoom;
        TextView hotelRoomCapacity;
        TextView hotelRoomPrice;
        TextView roomSchedule;

        public CustomerHotelRoomsHolder(@NonNull View itemView) {
            super(itemView);
            hotelRoomsLayout = itemView.findViewById(R.id.layoutRooms);
            bedsInRoom = itemView.findViewById(R.id.roomBeds);
            bedSizesInRoom = itemView.findViewById(R.id.roomBedSize);
            hotelRoomCapacity = itemView.findViewById(R.id.roomCapacity);
            hotelRoomPrice = itemView.findViewById(R.id.roomPrice);
            roomSchedule = itemView.findViewById(R.id.roomSchedule);
        }

        public void bindRoom(CustomerHotelRoomsModel hotelRoomModel) {
            //bedsInRoom.setText(hotelRoom.getBedsInRoomCount());
            //bedSizeInRoom.setText(hotelRoom.getRelated().toString());

            bedsInRoom.setText("Beds: " + String.valueOf(hotelRoomModel.getBedsCount()));
            bedSizesInRoom.setText("Sizes: " + hotelRoomModel.getBedTypes());
            hotelRoomCapacity.setText("Capacity: " + String.valueOf(hotelRoomModel.getCapacity()));
            hotelRoomPrice.setText("$" + String.valueOf(hotelRoomModel.getPrice()));
            roomSchedule.setText("Schedule: " + hotelRoomModel.getRoomAvailability());

            hotelRoomsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("hi");
                    //v.getContext().startActivity(new Intent(v.getContext(), CustomerHotelRoomsActivity.class).putExtra("Hotel Room", hotelRoom));
                }
            });

        }
    }
}
