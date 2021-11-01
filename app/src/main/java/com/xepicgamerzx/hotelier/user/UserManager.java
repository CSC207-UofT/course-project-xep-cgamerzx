package com.xepicgamerzx.hotelier.user;

import android.content.Context;

import com.xepicgamerzx.hotelier.storage.FileReadWrite;
import com.xepicgamerzx.hotelier.user.model.User;

public class UserManager {
    FileReadWrite fw = new FileReadWrite();

    public void saveUser(User user, Context context) {
        fw.writeData(user, "file.dat", context);
    }

    public Object getUser(Context context) {
        Object user = fw.readData("file.dat", context);
        return user;
    }

    public void signOut(Context context) {
        fw.writeData(null, "file.dat", context);
    }
}
