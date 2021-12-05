package com.xepicgamerzx.hotelier.objects.OldObjects;

import java.util.List;

public interface IDataBase {
    void save();

    List<HotelOld> read();
}
