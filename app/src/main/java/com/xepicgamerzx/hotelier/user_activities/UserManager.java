package com.xepicgamerzx.hotelier.user_activities;

import android.app.Application;
import android.content.Context;

import com.xepicgamerzx.hotelier.storage.FileReadWrite;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.UserDao;
import com.xepicgamerzx.hotelier.storage.hotel_managers.DiscreteManager;
import com.xepicgamerzx.hotelier.storage.user.model.User;

import java.util.List;

public class UserManager implements DiscreteManager<User, Long, Long[]> {
    private static volatile UserManager INSTANCE;

    private final HotelierDatabase db;
    private final UserDao userDao;

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

    public

    @Deprecated
    FileReadWrite<User> fw = new FileReadWrite<>();
    @Deprecated
    public void saveUser(User user, Context context) {
        fw.writeData(user, "file.dat", context);
    }
    @Deprecated
    public User getUser(Context context) {
        // error when no file.dat, how to fix?
        try{
        if (fw.readData("file.dat", context) != null) {
            return fw.readData("file.dat", context);
        }}
        catch (java.lang.ClassCastException e){
            return null;
        }
        return null;
    }
    @Deprecated
    public void signOut(Context context) {
        fw.writeData(null, "file.dat", context);
    }

    /**
     * Gets objects with matching primary key IDs.
     *
     * @param id <I> id(s) acting as primary key to be searched for.
     * @return List<T> of objects with primary keys that match id(s).
     */
    @Override
    public List<User> get(Long... id) {
        return userDao.getIdMatch(id);
    }

    /**
     * Gets all instances of <T> in the database.
     *
     * @return List<T> saved in the database.
     */
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    /**
     * Updates User object(s) in the database.
     *
     * @param users User object(s) to be updated in the database.
     */
    @Override
    public void update(User... users) {
        userDao.update(users);
    }

    /**
     * Closes the manager instance if it is open.
     */
    @Override
    public void close() {
        INSTANCE = null;
    }

    /**
     * Inserts User objects to their database.
     *
     * @param users User object(s) to be inserted into the database.
     * @return Long[] auto-generated IDs of inserted objects.
     */
    @Override
    public Long[] insert(User... users) {
        return userDao.insert(users).toArray(new Long[0]);
    }

    /**
     * Get User objects associated with login information
     *
     * @param userId String userId
     * @param password String password
     * @return Long[] auto-generated IDs of inserted objects.
     */
    public User logIn(String userId, String password){
        return userDao.login(userId, password);
    }
}
