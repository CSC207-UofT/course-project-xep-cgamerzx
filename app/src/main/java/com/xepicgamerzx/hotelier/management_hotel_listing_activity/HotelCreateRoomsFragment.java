package com.xepicgamerzx.hotelier.management_hotel_listing_activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Objects;

/**
 * Fragment for hotel room creation
 */
public class HotelCreateRoomsFragment extends Fragment {
    Long startDate;
    Long endDate;
    String bedType;
    boolean isBedTypeSelected = false;

    ZoneId zoneId;
    MaterialButton schedule;
    TextInputEditText capacity;
    TextInputEditText pricePerNight;
    TextInputEditText totalBeds;
    MaterialButton saveRoom;
    ChipGroup chipGroup;
    ImageButton closeBtn;

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
        setRoomFields(v);
        buildDateSelection();
        saveRoomListener();
        bedSizeChipsListener(v);
        closeBtn.setOnClickListener(v1 -> requireActivity().onBackPressed());

        return v;
    }

    private void bedSizeChipsListener(View v) {
        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = v.findViewById(checkedId);

            if (chip != null) {
                bedType = chip.getText().toString();
                isBedTypeSelected = true;
            } else {
                isBedTypeSelected = false;
            }
        });
    }

    private void saveRoomListener() {
        saveRoom.setOnClickListener(v12 -> {
            HotelCreatorActivity activity = (HotelCreatorActivity) getActivity();
            String stringErrorMess = validateRoomInputs();

            if (!stringErrorMess.equals("")) {
                Toast.makeText(getContext(), stringErrorMess, Toast.LENGTH_SHORT).show();
            } else {
                long roomId = Objects.requireNonNull(activity).manage.roomManager.createRoomId(
                        zoneId, startDate, endDate,
                        Integer.parseInt(Objects.requireNonNull(capacity.getText()).toString()),
                        BigDecimal.valueOf(Long.parseLong(Objects.requireNonNull(pricePerNight.getText()).toString()))
                );
                // The parent activity is HotelCreator (where these variables can be found)
                activity.viewModel.addRoomId(roomId);
                String bedId = activity.manage.bedManager.createId(bedType);
                activity.manage.roomBedsCrossManager.createRelationship(roomId, bedId, Integer.parseInt(Objects.requireNonNull(totalBeds.getText()).toString()));

                activity.text += "\n" + roomId;
                activity.isRoomsMade = true;
                activity.addRoomsBtn.setText(R.string.add_another_room);

                requireActivity().onBackPressed();
            }
        });
    }

    private void buildDateSelection() {
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("SELECT A CHECK IN AND CHECKOUT DATE");
        final MaterialDatePicker<Pair<Long, Long>> materialDatePicker = builder.build();

        schedule.setOnClickListener(v13 -> materialDatePicker.show(getParentFragmentManager(), "DATE_RANGE_PICKER"));

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            startDate = selection.first;
            endDate = selection.second;

            // Converts to normal date
            String dates = UnixEpochDateConverter.epochToReadable(startDate, endDate);
            schedule.setText(dates);
        });
    }

    private void setRoomFields(View v) {
        zoneId = ZoneId.systemDefault();
        schedule = v.findViewById(R.id.setScheduleBtn);
        capacity = v.findViewById(R.id.roomCapacity);
        pricePerNight = v.findViewById(R.id.pricePerNight);
        totalBeds = v.findViewById(R.id.totalBeds);
        saveRoom = v.findViewById(R.id.saveRoomBtn);
        chipGroup = v.findViewById(R.id.chip_group_choice);
        closeBtn = v.findViewById(R.id.closeBtn);
    }

    private String validateRoomInputs() {
        String s = "";
        if (!(Objects.requireNonNull(capacity.getText()).toString().matches("\\d+") &&
                Objects.requireNonNull(totalBeds.getText()).toString().matches("\\d+") &&
                Objects.requireNonNull(pricePerNight.getText()).toString().matches("\\d+"))) {
            s = "Input invalid, enter an integer";
        }
        if (startDate == null || endDate == null || !(isBedTypeSelected)) {
            s = "Enter all fields";
        }

        return s;
    }
}