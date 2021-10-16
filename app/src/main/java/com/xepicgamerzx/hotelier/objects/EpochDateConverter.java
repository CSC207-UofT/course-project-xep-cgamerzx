package com.xepicgamerzx.hotelier.objects;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class EpochDateConverter {
    // Epoch to local date

    public String epochToLocal(Long date) {
        LocalDate sd = Instant.ofEpochMilli(date)
                .atZone(ZoneId.systemDefault()).toLocalDate();

        return String.format("%s", sd);
    }

    public String epochToLocal(Long date1, Long date2) {
        LocalDate sd = Instant.ofEpochMilli(date1)
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ed = Instant.ofEpochMilli(date2)
                .atZone(ZoneId.systemDefault()).toLocalDate();


        return String.format("%s - %s", sd, ed);
    }

}
