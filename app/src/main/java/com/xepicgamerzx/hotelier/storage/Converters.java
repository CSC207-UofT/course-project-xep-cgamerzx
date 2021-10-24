package com.xepicgamerzx.hotelier.storage;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.ZoneId;

public class Converters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static Date toDate(Long dateLong) {
        return dateLong == null ? null : new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static ZoneId fromStringToZoneID(String strZoneID) {
        return ZoneId.of(strZoneID);
    }

    @TypeConverter
    public static String fromZoneIDToString(ZoneId zoneId) {
        return zoneId.toString();
    }

    @TypeConverter
    public BigDecimal fromLong(Long value) {
        return value == null ? null : new BigDecimal(value).divide(new BigDecimal(100));
    }

    @TypeConverter
    public Long toLong(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        } else {
            return bigDecimal.multiply(new BigDecimal(100)).longValue();
        }
    }
}