package com.xepicgamerzx.hotelier.customer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.xepicgamerzx.hotelier.R;

public class CustomerActivity extends AppCompatActivity {

    private Button datePickerBtn;

    private TextView selectedDateRangeText;
    // test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        datePickerBtn = findViewById(R.id.select_dates_btn);
        selectedDateRangeText = findViewById(R.id.selected_date_range_txt);

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("SELECT A CHECK IN AND CHECKOUT DATE");
        final MaterialDatePicker materialDatePicker = builder.build();

        datePickerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                materialDatePicker.show(getSupportFragmentManager(),  "DATE_RANGE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
        selectedDateRangeText.setText(materialDatePicker.getHeaderText());
            }
        });
    }

    /** Search for valid room listings given the customer's parameters. Display fragment list when
     * completed
     */
    public void searchListings(){

        // Send return fragment information
        Bundle bundle = new Bundle();
        bundle.putString("edttext", "From Activity");
        // set Fragmentclass Arguments
        RoomsReturnFragment roomsReturnF = new RoomsReturnFragment();
        roomsReturnF.setArguments(bundle);
    }
}