package com.xepicgamerzx.hotelier.objects;

import android.icu.util.ULocale;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

public class UnixEpochDateConverter {
    // Epoch to local date

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

    public String epochToReadable(long date1, long date2) {
        String localDate1 = epochToLocal(date1);
        String localDate2 = epochToLocal(date2);

        LocalDate d1 = LocalDate.parse(localDate1);
        LocalDate d2 = LocalDate.parse(localDate2);
        return String.format("%s, %s/%s - %s, %s/%s", d1.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault()), d1.getMonthValue(), d1.getDayOfMonth(),
                d2.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault()), d2.getMonthValue(), d2.getDayOfMonth());
    }

}
