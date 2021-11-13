package com.xepicgamerzx.hotelier.user.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.xepicgamerzx.hotelier.user.model.User;


@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public static final String dbName = "user";
    private static UserDatabase userDatabase;

    public static synchronized UserDatabase getUserDatabase(Context context) {
        if (userDatabase == null) {
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return userDatabase;
    };

    public abstract UserDAO userDao();
}
