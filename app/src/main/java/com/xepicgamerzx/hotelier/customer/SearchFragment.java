package com.xepicgamerzx.hotelier.customer;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.core.util.Pair;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.objects.UnixEpochDateConverter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    int numberOfGuests;

    private List<DestinationItem> destinationList;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        fillDestinationList();

        AutoCompleteTextView editText = v.findViewById(R.id.selectDestination);
        AutoDestinationAdapter adapter = new AutoDestinationAdapter(this.getContext(), destinationList);
        editText.setAdapter(adapter);

        ImageButton backBtn = v.findViewById(R.id.backBtn);
        ImageButton addGuestBtn = v.findViewById(R.id.btnAdd);
        ImageButton minusGuestBtn = v.findViewById(R.id.btnMinus);
        TextView numGuests = v.findViewById(R.id.textNumGuests);
        Button dateSelection = v.findViewById(R.id.dateSelection);

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("SELECT A CHECK IN AND CHECKOUT DATE");
        final MaterialDatePicker<Pair<Long, Long>> materialDatePicker = builder.build();

        // Listeners
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        addGuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGuests();
                numGuests.setText(Integer.toString(numberOfGuests));
            }
        });

        minusGuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusGuests();
                numGuests.setText(Integer.toString(numberOfGuests));
            }
        });

        dateSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "DATE_RANGE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                Long startDate = selection.first;
                Long endDate = selection.second;

                // Converts to normal date
                UnixEpochDateConverter epoch = new UnixEpochDateConverter();
                String dates = epoch.epochToReadable(startDate, endDate);
                dateSelection.setText(dates);

                // Save Dates Somewhere.
                //dateRange.put("startDate", startDate);
                //dateRange.put("endDate", endDate);
            }
        });

        return v;
    }

    public void addGuests() {
        this.numberOfGuests += 1;
    }

    public void minusGuests() {
        if(this.numberOfGuests != 1) {
            this.numberOfGuests -= 1;
        }
    }

    private void fillDestinationList() {
        destinationList = new ArrayList<>();

        // Testing data.
        destinationList.add(new DestinationItem("Toronto", "Ontario", "Canada"));
        destinationList.add(new DestinationItem("Ottawa", "Ontario", "Canada"));
        destinationList.add(new DestinationItem("New York City", "New York", "United States"));


    }
}