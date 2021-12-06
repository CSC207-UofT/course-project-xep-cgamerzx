package com.xepicgamerzx.hotelier.home_page_activities;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favourites, container, false);

        Manage manage = Manage.getManager(getActivity().getApplication());
        RecyclerView hotelsRecyclerView = v.findViewById(R.id.favouritesView);

        HotelierDatabase hotelierDatabase = HotelierDatabase.getDatabase(v.getContext());
        UserManager userManager = UserManager.getManager(hotelierDatabase);
        userManager.setLastLoggedInUser();
        User user = userManager.user;

        if (userManager.isLoggedIn()){
            System.out.println("Logged in");
            List<Hotel> hotels = manage.hotelManager.getFavouriteHotels(user);
            System.out.println(hotels);
            //hotelsView = manage.hotelManager.generateHotelModel(hotels);
            Collections.reverse(hotelsView); // Reversing for newest favourites at the top
            hotelsAdapter = new HotelViewAdapter(hotelsView, this);

            hotelsRecyclerView.setAdapter(hotelsAdapter);

        } else {
            System.out.println("Not logged in.");
        }

        return v;
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