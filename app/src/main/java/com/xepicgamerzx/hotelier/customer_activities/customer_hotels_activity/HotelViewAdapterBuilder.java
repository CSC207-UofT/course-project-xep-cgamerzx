package com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity;

import android.app.Application;

import androidx.annotation.Nullable;

import com.xepicgamerzx.hotelier.home_page_activities.OnFavouriteClickListener;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Builder for HotelView adapters.
 */
public class HotelViewAdapterBuilder {
    private final HotelierDatabase db;
    private final Manage manage;

    @Nullable
    private Integer minCapacity;
    @Nullable
    private Double latitude;
    @Nullable
    private Double longitude;
    @Nullable
    private Long startDate;
    @Nullable
    private Long endDate;
    private boolean reverse = false;
    private boolean useFavourites = false;
    @Nullable
    private OnFavouriteClickListener onFavouriteClickListener;

    /**
     * Builder for Hotel View Adapter
     *
     * @param application Current application
     */
    public HotelViewAdapterBuilder(Application application) {
        db = HotelierDatabase.getDatabase(application);
        manage = Manage.getManager(application);
    }

    /**
     * Set the location data of the hotel view.
     *
     * @param latitude  Double center latitude
     * @param longitude Double center longitude
     * @return HotelViewAdapterBuilder
     */
    public HotelViewAdapterBuilder setLatLong(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        return this;
    }

    /**
     * Set the min capacity of the hotel view.
     *
     * @param minCapacity Integer min capacity.
     * @return HotelViewAdapterBuilder
     */
    public HotelViewAdapterBuilder setMinCapacity(Integer minCapacity) {
        this.minCapacity = minCapacity;
        return this;
    }

    /**
     * Set the schedule of the hotel view
     *
     * @param startDate Long startDate
     * @param endDate   Long endDate
     * @return HotelViewAdapterBuilder
     */
    public HotelViewAdapterBuilder setSchedule(Long startDate, Long endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }

    /**
     *
     * @param reverse
     * @return
     */
    public HotelViewAdapterBuilder setReverse(boolean reverse) {
        this.reverse = reverse;
        return this;
    }

    public HotelViewAdapterBuilder useFavourites(boolean useFavourites){
        this.useFavourites = useFavourites;
        return this;
    }

    public HotelViewAdapterBuilder setOnFavouriteClickListener(OnFavouriteClickListener onFavouriteClickListener) {
        this.onFavouriteClickListener = onFavouriteClickListener;
        return this;
    }

    /**
     * Generate hotel view adapter.
     *
     * @return HotelViewAdapter
     */
    public HotelViewAdapter build() {
        List<HotelViewModel> hotelViewModels;
        Map<Hotel, List<HotelRoom>> hotelListMap;

        if (useFavourites){
            hotelListMap = manage.hotelRoomMapManager.getFavourites();
        } else if (minCapacity != null && startDate != null && endDate != null && latitude != null && longitude != null) {
            // Capacity, schedule, location
            hotelListMap = manage.hotelRoomMapManager.getAvailableRooms(minCapacity, startDate, endDate, latitude, longitude);
        } else if (minCapacity != null && startDate != null && endDate != null) {
            // Capacity, schedule
            hotelListMap = manage.hotelRoomMapManager.getAvailableRooms(minCapacity, startDate, endDate);
        } else if (minCapacity != null && latitude != null && longitude != null) {
            // Capacity, location
            hotelListMap = manage.hotelRoomMapManager.getAvailableRooms(minCapacity, latitude, longitude);
        } else if (minCapacity != null) {
            // Capacity
            hotelListMap = manage.hotelRoomMapManager.getAvailableRooms(minCapacity);
        } else {
            // No filtering
            hotelListMap = manage.hotelRoomMapManager.getAvailableRooms();
        }

        hotelViewModels = generateHotelModel(hotelListMap);
        return generateHotelViewAdapter(hotelViewModels);
    }


    /**
     * Generates a list of HotelViewModel's with specifics
     */
    private List<HotelViewModel> generateHotelModel(Map<Hotel, List<HotelRoom>> hotelListMap) {
        List<HotelViewModel> hotelsView = new ArrayList<>();

        hotelListMap.forEach((hotel, rooms) ->
                hotelsView.add(new HotelViewModelBuilder()
                        .setName(hotel.getName())
                        .setAddress(hotel.getAddress()
                                .getFullStreet())
                        .setPriceRange(manage.roomManager.getPriceRange(hotel).get(0))
                        .setNumberOfRooms(rooms.size())
                        .setHotel(hotel).setRooms(rooms)
                        .createHotelViewModel()));
        if (reverse) Collections.reverse(hotelsView);

        return hotelsView;
    }

    private HotelViewAdapter generateHotelViewAdapter(List<HotelViewModel> hotelViewModels) {
        if (onFavouriteClickListener != null) {
            return new HotelViewAdapter(hotelViewModels, onFavouriteClickListener);
        } else if (startDate != null && endDate != null) {
            return new HotelViewAdapter(hotelViewModels, startDate, endDate);
        }
        return new HotelViewAdapter(hotelViewModels);
    }
}
