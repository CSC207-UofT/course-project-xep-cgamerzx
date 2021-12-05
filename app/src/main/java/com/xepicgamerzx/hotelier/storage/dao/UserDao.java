package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.storage.user.model.User;

/**
 * Data access object for users.
 */
@Dao
public abstract class UserDao implements BaseDao<Void, User> {
    /**
     * TODO
     *
     * @param userId
     * @param password
     * @return
     */
    @Query("SELECT * from users where userId=(:userId) and password=(:password)")
    public abstract User login(String userId, String password);

    // TODO - Add a userFavourites column, so when a user clicks favourite on something, it gets saved.

    /**
     * Delete all Users from User table.
     */
    @Query("DELETE FROM Users")
    public abstract void deleteAll();
}
