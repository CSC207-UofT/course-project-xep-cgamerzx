package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.xepicgamerzx.hotelier.storage.user.model.User;

@Dao
public abstract class UserDAO implements  BaseDao<Void, User>{
    @Query("SELECT * from users where userId=(:userId) and password=(:password)")
    public abstract User login( String userId, String password);

    // TODO - Add a userFavourites column, so when a user clicks favourite on something, it gets saved.

}
