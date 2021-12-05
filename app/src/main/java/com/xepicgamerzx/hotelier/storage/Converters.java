package com.xepicgamerzx.hotelier.storage;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;

public class Converters {

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
    public static ArrayList<Long> fromStringToArrayList(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(String.valueOf(value), listType);
    }

    @TypeConverter
    public static String fromArrayListToString(ArrayList<Long> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public BigDecimal fromLongToBig(Long value) {
        return value == null ? null : new BigDecimal(value)
                .divide(new BigDecimal(100), 2, RoundingMode.UNNECESSARY);
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