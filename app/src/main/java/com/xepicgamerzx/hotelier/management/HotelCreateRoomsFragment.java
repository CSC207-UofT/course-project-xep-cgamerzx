package com.xepicgamerzx.hotelier.management;

import android.os.Bundle;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;

import java.math.BigDecimal;
import java.time.ZoneId;

public class HotelCreateRoomsFragment extends Fragment {
    Long startDate;
    Long endDate;
    String bedType;

    public HotelCreateRoomsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hotel_create_rooms, container, false);

        ZoneId zoneId = ZoneId.systemDefault();
        MaterialButton schedule = v.findViewById(R.id.setScheduleBtn);
        TextInputEditText capacity = v.findViewById(R.id.roomCapacity);
        TextInputEditText pricePerNight = v.findViewById(R.id.pricePerNight);
        TextInputEditText totalBeds = v.findViewById(R.id.totalBeds);
        MaterialButton saveRoom = v.findViewById(R.id.saveRoomBtn);
        ChipGroup chipGroup = v.findViewById(R.id.chip_group_choice);

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("SELECT A CHECK IN AND CHECKOUT DATE");
        final MaterialDatePicker<Pair<Long, Long>> materialDatePicker = builder.build();

        saveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotelCreatorActivity activity = (HotelCreatorActivity) getActivity();

                // Validate input later
                HotelRoom room = activity.roomManager.createRoom(
                        zoneId, startDate, endDate,
                        Integer.parseInt(capacity.getText().toString()),
                        BigDecimal.valueOf(Long.parseLong(pricePerNight.getText().toString()))
                );

//                // Beds
//                for(int i = 0; i < Integer.parseInt(totalBeds.getText().toString()); i++){
//
//                }

                System.out.println(room);
//                activity.hotelRooms.add(room);
            }
        });

        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip chip = v.findViewById(checkedId);
                bedType = chip.getText().toString();
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getParentFragmentManager(), "DATE_RANGE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                startDate = selection.first;
                endDate = selection.second;

                // Converts to normal date
                UnixEpochDateConverter epoch = new UnixEpochDateConverter();
                String dates = epoch.epochToReadable(startDate, endDate);
                schedule.setText(dates);
            }
        });

        return v;
    }
}