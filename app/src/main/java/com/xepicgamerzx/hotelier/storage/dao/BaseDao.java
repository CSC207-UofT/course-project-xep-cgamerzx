package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

public interface BaseDao <Q, T>{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Q insert(T... obj);

    @Update
    int update(T... obj);

    @Delete
    void delete(T obj);
}
