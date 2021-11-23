package com.xepicgamerzx.hotelier.storage.hotelier_database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.xepicgamerzx.hotelier.objects.cross_reference_objects.HotelAmenitiesCrossRef;
import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomAmenitiesCrossRef;
import com.xepicgamerzx.hotelier.objects.cross_reference_objects.RoomBedsCrossRef;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Bed;
import com.xepicgamerzx.hotelier.objects.hotel_objects.Hotel;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelAmenity;
import com.xepicgamerzx.hotelier.objects.hotel_objects.HotelRoom;
import com.xepicgamerzx.hotelier.objects.hotel_objects.RoomAmenity;
import com.xepicgamerzx.hotelier.storage.Converters;
import com.xepicgamerzx.hotelier.storage.dao.BedDao;
import com.xepicgamerzx.hotelier.storage.dao.BedRoomCrossDao;
import com.xepicgamerzx.hotelier.storage.dao.HotelAmenitiesCrossDao;
import com.xepicgamerzx.hotelier.storage.dao.HotelAmenityDao;
import com.xepicgamerzx.hotelier.storage.dao.HotelDao;
import com.xepicgamerzx.hotelier.storage.dao.RoomAmenitiesCrossDao;
import com.xepicgamerzx.hotelier.storage.dao.RoomAmenityDao;
import com.xepicgamerzx.hotelier.storage.dao.RoomDao;
import com.xepicgamerzx.hotelier.storage.dao.UserDao;
import com.xepicgamerzx.hotelier.storage.user.model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters({Converters.class})
@Database(entities = {Hotel.class, HotelRoom.class, Bed.class, HotelAmenity.class,
        RoomAmenity.class, RoomBedsCrossRef.class, HotelAmenitiesCrossRef.class,
        RoomAmenitiesCrossRef.class, User.class},
        version = 1)
public abstract class HotelierDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 2;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile HotelierDatabase INSTANCE;

    public static HotelierDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), HotelierDatabase.class, "hotelier_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract HotelDao hotelDao();

    public abstract RoomDao roomDao();

    public abstract BedDao bedDao();

    public abstract HotelAmenityDao hotelAmenityDao();

    public abstract RoomAmenityDao roomAmenityDao();

    public abstract BedRoomCrossDao bedRoomCrossDao();

    public abstract HotelAmenitiesCrossDao hotelAmenitiesCrossDao();

    public abstract RoomAmenitiesCrossDao roomAmenitiesCrossDao();

    public abstract UserDao userDao();
}
