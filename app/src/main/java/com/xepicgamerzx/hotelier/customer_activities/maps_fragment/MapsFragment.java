package com.xepicgamerzx.hotelier.customer_activities.maps_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.xepicgamerzx.hotelier.R;


public class MapsFragment extends Fragment {
    double latitude;
    double longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_maps, container, false);

        Bundle bundle = getArguments();

        if(bundle!=null){
            longitude = bundle.getDouble("longitude");
            latitude = bundle.getDouble("latitude");
            System.out.println(longitude);
            System.out.println(latitude);
        }

        // Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.googleMaps);

        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                if(latitude != 0  && longitude != 0) {
                    LatLng hotelCoord = new LatLng(latitude, longitude);
                    googleMap.addMarker(new MarkerOptions().position(hotelCoord));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom (
                            hotelCoord, 15
                    ));
                }

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        // When the map is clicked,
                        MarkerOptions markerOptions = new MarkerOptions();

                        // Set position
                        markerOptions.position(latLng);

                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        googleMap.clear();

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom (
                                latLng, 10
                        ));

                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });

        return v;
    }
}