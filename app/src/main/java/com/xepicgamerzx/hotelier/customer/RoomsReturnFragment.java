package com.xepicgamerzx.hotelier.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xepicgamerzx.hotelier.R;

class RoomsReturnFragment extends Fragment {

    private TextView roomsTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        roomsTextView = getView().findViewById(R.id.rooms_return_txt);

        String roomsText = getArguments().getString("edttext");

        roomsTextView.setText(roomsText);
        return inflater.inflate(R.layout.fragment_rooms_return, container, false);
    }
}