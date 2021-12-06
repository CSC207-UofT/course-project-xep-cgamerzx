package com.xepicgamerzx.hotelier.customer_activities.customer_hotels_activity;

import android.app.Application;

import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.Manage;

public class HotelViewModelListBuilder {
    private HotelierDatabase db;
    private Manage manage;
    private Integer capacity;
    private Long latitude;
    private Long longitude;

    private HotelViewModelListBuilder(Application application){
        db = HotelierDatabase.getDatabase(application);
        manage = Manage.getManager(application);
    }
}
