package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface ClearTablesDao {

    @Query("DELETE FROM HotelRoom")
    void nukeTable();

}
