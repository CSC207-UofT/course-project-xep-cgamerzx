package com.xepicgamerzx.hotelier.objects.OldObjects;

import java.util.List;

@Deprecated
public interface IDataBase {
    void save();

    List<HotelOld> read();
}
