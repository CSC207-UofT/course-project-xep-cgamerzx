package com.xepicgamerzx.hotelier.objects;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class UnixEpochDateConverter {
    // Epoch to local date

    public static String epochToReadable(long date1, long date2) {

        ZonedDateTime dt1 = Instant.ofEpochMilli(date1).atZone(ZoneOffset.UTC);
        String day1 = dt1.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
        String month = String.valueOf(dt1.getMonthValue());
        String dayOfMonth = String.valueOf(dt1.getDayOfMonth());

        ZonedDateTime dt2 = Instant.ofEpochMilli(date2).atZone(ZoneOffset.UTC);
        String day2 = dt2.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
        String month2 = String.valueOf(dt2.getMonthValue());
        String dayOfMonth2 = String.valueOf(dt2.getDayOfMonth());
        System.out.println(date1);
        return String.format("%s, %s/%s - %s, %s/%s", day1, month, dayOfMonth, day2, month2, dayOfMonth2);
    }

    /**
     * Converting an epoch date to a format called LocalDate (ex. "2021-07-08")
     *
     * @param date A date in epoch format.
     * @return A String in LocalDate format.
     */
    public String epochToLocal(long date) {
        LocalDate sd = Instant.ofEpochMilli(date)
                .atZone(ZoneId.systemDefault()).toLocalDate();

        return String.format("%s", sd);
    }

    /**
     * Overloading the method above to format two epoch dates instead of one.
     *
     * @param date1 Epoch date
     * @param date2 Epoch date
     * @return String
     */
    public String epochToLocal(long date1, long date2) {
        LocalDate sd = Instant.ofEpochMilli(date1)
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ed = Instant.ofEpochMilli(date2)
                .atZone(ZoneId.systemDefault()).toLocalDate();

        return String.format("%s - %s", sd, ed);
    }

}
