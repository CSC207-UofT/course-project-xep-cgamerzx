package com.xepicgamerzx.hotelier.home_page_activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.xepicgamerzx.hotelier.R;
import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewAdapter;
import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewAdapterBuilder;
import com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity.HotelViewModel;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;
import com.xepicgamerzx.hotelier.storage.user.UserManager;

import java.util.List;

/**
 * Favourites fragment
 */
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

    private void setAllFields(View v) {
        hotelsRecyclerView = v.findViewById(R.id.favouritesView);
        manage = Manage.getManager(requireActivity().getApplication());
        hotelierDatabase = HotelierDatabase.getDatabase(v.getContext());
        userManager = UserManager.getManager(hotelierDatabase);
        userManager.setLastLoggedInUser(v.getContext());
        signedOutTxt = v.findViewById(R.id.isSignedIn);
    }

    private void setProfileVisibility() {
        if (userManager.isLoggedIn()) {
            System.out.println("Logged in");
            setRecyclerView();
        } else {
            System.out.println("Not logged in.");
            signedOutTxt.setVisibility(View.VISIBLE);
        }
    }

    private void setRecyclerView() {
        hotelsAdapter = new HotelViewAdapterBuilder(requireActivity().getApplication())
                .useFavourites(true)
                .setReverse(true)
                .setOnFavouriteClickListener(this)
                .build();
        hotelsView = hotelsAdapter.hotels;
        hotelsRecyclerView.setAdapter(hotelsAdapter);
    }

    /**
     * Clicking favourite on the user's favourites removes it from their favourites list.
     *
     * @param position position of the item in the user's favourites
     */
    @Override
    public void onFavouriteClick(int position) {
        hotelsView.remove(position);
        hotelsAdapter.notifyItemRemoved(position);
    }
}