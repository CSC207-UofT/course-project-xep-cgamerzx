package com.xepicgamerzx.hotelier;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xepicgamerzx.hotelier.objects.Hotel;
import com.xepicgamerzx.hotelier.storage.HotelManager;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.HotelDao;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class HotelMangerTest {

    private HotelManager hotelManager;

    @Before
    public void createDb() {
        Application application = ApplicationProvider.getApplicationContext();
        hotelManager = new HotelManager(application);
    }

    @After
    public void closeDb() throws IOException {
        hotelManager.closeDB();
    }
}
