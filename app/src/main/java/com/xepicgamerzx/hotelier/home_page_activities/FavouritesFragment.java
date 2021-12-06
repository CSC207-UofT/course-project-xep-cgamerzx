package com.xepicgamerzx.hotelier.home_page_activities;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewAdapter;
import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewModel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;
import com.xepicgamerzx.hotelier.storage.user.model.User;
import com.xepicgamerzx.hotelier.storage.user.UserManager;

import java.util.Collections;
import java.util.List;


public class FavouritesFragment extends Fragment implements OnFavouriteClickListener {
    List<HotelViewModel> hotelsView;
    HotelViewAdapter hotelsAdapter;
    Manage manage;
    UserManager userManager;
    HotelierDatabase hotelierDatabase;
    RecyclerView hotelsRecyclerView;
    TextView signedOutTxt;

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favourites, container, false);

        setAllFields(v);
        setProfileVisibility();

        return v;
    }

    public void setAllFields(View v) {
        hotelsRecyclerView = v.findViewById(R.id.favouritesView);
        manage = Manage.getManager(getActivity().getApplication());
        hotelierDatabase = HotelierDatabase.getDatabase(v.getContext());
        userManager = UserManager.getManager(hotelierDatabase);
        userManager.setLastLoggedInUser(v.getContext());
        signedOutTxt = v.findViewById(R.id.isSignedIn);
    }

    public void setProfileVisibility() {
        if (userManager.isLoggedIn()){
            System.out.println("Logged in");
            setRecyclerView();
        } else {
            System.out.println("Not logged in.");
            signedOutTxt.setVisibility(View.VISIBLE);
        }
    }

    public void setRecyclerView() {
        List<Hotel> hotels = manage.hotelManager.getFavouriteHotels(userManager.getUser());
        hotelsView = manage.hotelManager.generateHotelModel(hotels);
        Collections.reverse(hotelsView); // Reversing for newest favourites at the top
        hotelsAdapter = new HotelViewAdapter(hotelsView, this);
        hotelsRecyclerView.setAdapter(hotelsAdapter);
    }

    /**
     * Clicking favourite on the users favourites removes it from their favourites list.
     * @param position
     */
    @Override
    public void onFavouriteClick(int position) {
        hotelsView.remove(position);
        hotelsAdapter.notifyItemRemoved(position);
    }
}