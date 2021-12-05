package com.xepicgamerzx.hotelier.user_activities;

import android.app.Application;
import android.content.Context;

import com.xepicgamerzx.hotelier.storage.FileReadWrite;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.UserDao;
import com.xepicgamerzx.hotelier.storage.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager implements com.xepicgamerzx.hotelier.storage.hotel_managers.Manager<User, Long[]> {
    private static volatile UserManager INSTANCE;
    private final HotelierDatabase db;
    private final UserDao userDao;
    public static User user;

    @Deprecated
    FileReadWrite<User> fw = new FileReadWrite<>();

    private UserManager(Application application) {
        db = HotelierDatabase.getDatabase(application);
        userDao = db.userDao();
    }

    public UserManager(HotelierDatabase dbInstance) {
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

    public void registerUser(User user) {
        userDao.insert(user);
    }

    public void login(String userIdText, String passwordText) {
        User user = userDao.login(userIdText, passwordText);
        this.setUser(user);
    }

    public List<User> getAllUsers() {
        System.out.println(userDao.getAll());
        return userDao.getAll();
    }

    public User getLastLoggedInUser() {
        List<User> users = userDao.getAll();
        return users.get(users.size() - 1);
    }

    public void setLastLoggedInUser() {
        List<User> users = userDao.getAll();
        user = users.get(users.size() - 1);
    }

    public void signOut() {
        this.user = null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public ArrayList<Long> getUserFavourites() {
        return this.user.getFavHotelIds();
    }

    public void updateUserFavourites(Long hotelID) {
        if (user.getFavHotelIds().contains(hotelID)) {
            user.removeFavHotel(hotelID);
        } else {
            user.addFavHotel(hotelID);
        }
        userDao.update(user);
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
