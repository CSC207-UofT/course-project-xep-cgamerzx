package com.xepicgamerzx.hotelier.storage.dao;

import androidx.room.Dao;
import androidx.room.Query;

@Deprecated // I don't think this is being used?
@Dao
public interface ClearTablesDao {

    @Query("DELETE FROM HotelRoom")
    void nukeTable();

}
