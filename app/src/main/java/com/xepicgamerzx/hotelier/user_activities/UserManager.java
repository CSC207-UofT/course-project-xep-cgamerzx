package com.xepicgamerzx.hotelier.user_activities;

import android.content.Context;

import com.xepicgamerzx.hotelier.storage.FileReadWrite;
import com.xepicgamerzx.hotelier.storage.user.model.User;

public class UserManager {
    FileReadWrite fw = new FileReadWrite();

    public void saveUser(User user, Context context) {
        fw.writeData(user, "file.dat", context);
    }

    public User getUser(Context context) {
        // error when no file.dat, how to fix?
        if (fw.readData("file.dat", context) != null) {
            Object user = fw.readData("file.dat", context);
            return (User) user;
        }
        return null;
    }

    public void signOut(Context context) {
        fw.writeData(null, "file.dat", context);
    }
}
