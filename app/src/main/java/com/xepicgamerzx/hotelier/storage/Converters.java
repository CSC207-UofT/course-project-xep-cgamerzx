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

    /**
     * Convert a String to a ZoneId.
     * @param strZoneID the id of the zone in string format
     * @return the id of the zone in ZoneId format
     */
    @TypeConverter
    public static ZoneId fromStringToZoneID(String strZoneID) {
        return ZoneId.of(strZoneID);
    }

    /**
     * Convert a ZoneId to a String.
     * @param zoneId the id of the zone in ZoneId format
     * @return the id of the zone in string format
     */
    @TypeConverter
    public static String fromZoneIDToString(ZoneId zoneId) {
        return zoneId.toString();
    }

    /**
     * Convert a String to an ArrayList.
     * @param value the value in string format
     * @return the value in an arraylist
     */
    @TypeConverter
    public static ArrayList<String> fromStringToStringArrayList(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    /**
     * Convert an ArrayList to a String.
     * @param list a list of values
     * @return the values in string format
     */
    @TypeConverter
    public static String fromStringArrayListToString(ArrayList<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    /**
     * Convert a Long value to a BigDecimal
     * @param value the value in long format
     * @return the value in big decimal format
     */
    @TypeConverter
    public BigDecimal fromLongToBig(Long value) {
        return value == null ? null : new BigDecimal(value)
                .divide(new BigDecimal(100), 2, RoundingMode.UNNECESSARY);
    }

    /**
     * Convert a BigDecimal to a Long.
     * @param bigDecimal the value in big decimal format
     * @return the value in long format
     */
    @TypeConverter
    public Long toLong(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        } else {
            return bigDecimal.multiply(new BigDecimal(100)).longValue();
        }
    }
}