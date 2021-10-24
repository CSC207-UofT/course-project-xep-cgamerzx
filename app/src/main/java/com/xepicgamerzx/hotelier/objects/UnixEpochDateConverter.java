package com.xepicgamerzx.hotelier.objects;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class UnixEpochDateConverter {
    // Epoch to local date

    /**
     * Converting an epoch date to a format called LocalDate (ex. "2021-07-08")
     *
     * @param date A date in epoch format.
     * @return A String in LocalDate format.
     */
    public String epochToLocal(Long date) {
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
    public String epochToLocal(Long date1, Long date2) {
        LocalDate sd = Instant.ofEpochMilli(date1)
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ed = Instant.ofEpochMilli(date2)
                .atZone(ZoneId.systemDefault()).toLocalDate();

        return String.format("%s - %s", sd, ed);
    }

}
