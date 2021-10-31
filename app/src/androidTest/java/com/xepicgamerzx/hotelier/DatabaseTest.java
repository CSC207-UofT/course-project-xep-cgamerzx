package com.xepicgamerzx.hotelier;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.HotelDao;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private HotelDao hotelDao;
    private HotelierDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, HotelierDatabase.class).build();
        hotelDao = db.hotelDao();
    }

    @After
    public void closeDb(){
        db.close();
    }
}
