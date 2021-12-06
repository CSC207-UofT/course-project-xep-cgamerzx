package com.xepicgamerzx.hotelier.storage;

import android.app.Application;

import com.xepicgamerzx.hotelier.storage.hotel_managers.BedManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelAmenityManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.HotelManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.RoomAmenityManager;
import com.xepicgamerzx.hotelier.storage.hotel_managers.RoomManager;
import com.xepicgamerzx.hotelier.storage.hotel_reference_managers.HotelAmenitiesCrossManager;
import com.xepicgamerzx.hotelier.storage.hotel_reference_managers.RoomAmenitiesCrossManager;
import com.xepicgamerzx.hotelier.storage.hotel_reference_managers.RoomBedsCrossManager;

public final class Manage {
    private static volatile Manage INSTANCE;

    public volatile BedManager bedManager;
    public volatile HotelAmenityManager hotelAmenityManager;
    public volatile RoomAmenityManager roomAmenityManager;
    public volatile HotelManager hotelManager;
    public volatile RoomManager roomManager;

    public volatile HotelAmenitiesCrossManager hotelAmenitiesCrossManager;
    public volatile RoomAmenitiesCrossManager roomAmenitiesCrossManager;
    public volatile RoomBedsCrossManager roomBedsCrossManager;

    private Manage(Application application) {
        bedManager = BedManager.getManager(application);
        hotelAmenityManager = HotelAmenityManager.getManager(application);
        roomAmenityManager = RoomAmenityManager.getManager(application);
        hotelManager = HotelManager.getManager(application);
        roomManager = RoomManager.getManager(application);

        hotelAmenitiesCrossManager = HotelAmenitiesCrossManager.getManager(application);
        roomAmenitiesCrossManager = RoomAmenitiesCrossManager.getManager(application);
        roomBedsCrossManager = RoomBedsCrossManager.getManager(application);
    }

    private Manage(HotelierDatabase dbInstance) {
        bedManager = BedManager.getManager(dbInstance);
        hotelAmenityManager = HotelAmenityManager.getManager(dbInstance);
        roomAmenityManager = RoomAmenityManager.getManager(dbInstance);
        hotelManager = HotelManager.getManager(dbInstance);
        roomManager = RoomManager.getManager(dbInstance);

        hotelAmenitiesCrossManager = HotelAmenitiesCrossManager.getManager(dbInstance);
        roomAmenitiesCrossManager = RoomAmenitiesCrossManager.getManager(dbInstance);
        roomBedsCrossManager = RoomBedsCrossManager.getManager(dbInstance);
    }

    public static Manage getManager(Application application) {
        if (INSTANCE == null) INSTANCE = new Manage(application);
        return INSTANCE;
    }

    public static Manage getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) INSTANCE = new Manage(dbInstance);
        return INSTANCE;
    }

    public void close(){
        INSTANCE = null;
        bedManager.close();
        hotelAmenityManager.close();
        roomManager.close();
        hotelManager.close();
        roomManager.close();

        hotelAmenitiesCrossManager.close();
        roomAmenitiesCrossManager.close();
        roomBedsCrossManager.close();
    }
}
