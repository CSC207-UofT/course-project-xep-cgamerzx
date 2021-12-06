package com.xepicgamerzx.hotelier.user_activities;

import android.app.Application;
import android.content.Context;

import com.xepicgamerzx.hotelier.storage.FileReadWrite;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.UserDao;
import com.xepicgamerzx.hotelier.storage.user.model.User;

public class UserManager implements com.xepicgamerzx.hotelier.storage.hotel_managers.Manager {
    private static volatile UserManager INSTANCE;

    private final HotelierDatabase db;
    private final UserDao userDao;
    public

    @Deprecated
    FileReadWrite<User> fw = new FileReadWrite<>();

    private UserManager(Application application) {
        db = HotelierDatabase.getDatabase(application);
        userDao = db.userDao();
    }

    private UserManager(HotelierDatabase dbInstance) {
        db = dbInstance;
        userDao = db.userDao();
    }

    public static UserManager getManager(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new UserManager(application);
        }

        return INSTANCE;
    }

    public static UserManager getManager(HotelierDatabase dbInstance) {
        if (INSTANCE == null) {
            INSTANCE = new UserManager(dbInstance);
        }

        return INSTANCE;
    }

    @Deprecated
    public void saveUser(User user, Context context) {
        fw.writeData(user, "file.dat", context);
    }

    @Deprecated
    public User getUser(Context context) {
        // error when no file.dat, how to fix?
        try {
            if (fw.readData("file.dat", context) != null) {
                return fw.readData("file.dat", context);
            }
        } catch (java.lang.ClassCastException e) {
            return null;
        }
        return null;
    }

    @Deprecated
    public void signOut(Context context) {
        fw.writeData(null, "file.dat", context);
    }

    /**
     * Closes the manager instance if it is open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }
}
