package com.xepicgamerzx.hotelier.user.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.user.model.User;

@Dao
public interface UserDAO {
    @Insert
    void registerUser(User user);

    @Query("SELECT * from users where userId=(:userId) and password=(:password)")
    User login( String userId, String password);

    // TODO - Add a userFavourites column, so when a user clicks favourite on something, it gets saved.

}
