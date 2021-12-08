package com.xepicgamerzx.hotelier.storage.user;

import android.app.Application;
import android.content.Context;

import com.xepicgamerzx.hotelier.storage.FileReadWrite;
import com.xepicgamerzx.hotelier.storage.HotelierDatabase;
import com.xepicgamerzx.hotelier.storage.dao.UserDao;
import com.xepicgamerzx.hotelier.storage.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager implements com.xepicgamerzx.hotelier.storage.hotel_managers.Manager {
    private static volatile UserManager INSTANCE;
    private final HotelierDatabase db;
    private final UserDao userDao;
    FileReadWrite<String> fw = new FileReadWrite<>();
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return (user == null) ? null : user.getUserName();
    }

    /**
     * Register a new manager user.
     * @param user the user to be registered
     */
    public void registerUser(User user) {
        userDao.insert(user);
    }

    /**
     * Register a new manager user.
     * @param userId    the id of the user to be registered
     * @param password  the password of the user to be registered
     * @param email     the email address of the user to be registered
     */
    public void registerUser(String userId, String password, String email) {
        User user = new User(userId, password, email);
        userDao.insert(user);
    }

    /**
     * Logs in a manager user.
     * @param userIdText    the inputted username
     * @param passwordText  the inputted password
     * @param context       the context for the database
     */
    public void login(String userIdText, String passwordText, Context context) {
        User user = userDao.login(userIdText, passwordText);
        logInLocally(true, context);
        this.setUser(user);
    }

    /**
     * Gets all the users of the app.
     * @return list of users
     */
    public List<User> getAllUsers() {
        System.out.println(userDao.getAll());
        return userDao.getAll();
    }

    /**
     * Gets the user that was last logged in
     * @return  the last user that was logged in
     */
    public User getLastLoggedInUser() {
        List<User> users = userDao.getAll();
        return users.get(users.size() - 1);
    }

    public void setLastLoggedInUser(Context context) {
        List<User> users = userDao.getAll();
        if (users.size() != 0 && isSignedIn(context)) {
            user = users.get(users.size() - 1);
        }
    }

    public void signOut(Context context) {
        this.user = null;
        signOutLocally(context);
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public ArrayList<String> getUserFavourites() {
        return user.getFavHotelIds();
    }

    public void updateUserFavourites(String hotelID) {
        System.out.println(hotelID);
        if (user.getFavHotelIds().contains(hotelID)) {
            user.removeFavHotel(hotelID);
        } else {
            user.addFavHotel(hotelID);
        }

        userDao.update(user);
    }

    public void addRecentSearches(String destination) {
        user.addRecentSearches(destination);
        userDao.update(user);
    }

    public List<String> getRecentSearches() {
        return user.getRecentSearches();
    }


    // Storing log in state locally.
    public void logInLocally(boolean val, Context context) {
        if (val) {
            fw.writeData("Logged In", "file.dat", context);
        }
    }

    public boolean isSignedIn(Context context) {
        try {
            if (fw.readData("file.dat", context) != null) {
                return true;
            }
        } catch (java.lang.ClassCastException e) {
            return false;
        }
        return false;
    }

    public void signOutLocally(Context context) {
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
