package com.xepicgamerzx.hotelier.storage;

import com.xepicgamerzx.hotelier.objects.UniqueEntity;

public interface IUniqueManager  <T extends UniqueEntity> extends Manager<T, String, Void> {
}
