package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.storage.user.model.User;

import java.util.List;

/**
 * Data access object for users.
 */
@Dao
public abstract class UserDao implements BaseDao<Void, User> {
    /**
     * Get user with the matching userid and password
     *
     * @param userId   user id of the user
     * @param password password of the user
     * @return User with matching credentials
     */
    @Query("SELECT * from users where userId=(:userId) and password=(:password)")
    public abstract User login(String userId, String password);

    /**
     * Get all User in User table.
     *
     * @return List<User> list of all User in User table.
     */
    @Query("SELECT * FROM users")
    public abstract List<User> getAll();

    /**
     * Get User with matching IDs in User table.
     *
     * @param userId long ID of user.
     * @return List<User> list of all Users with IDs that match userId.
     */
    @Query("SELECT * FROM users WHERE id IN (:userId)")
    public abstract List<User> getIdMatch(Long... userId);

    /**
     * Delete all Users from User table.
     */
    @Query("DELETE FROM Users")
    public abstract void deleteAll();
}
