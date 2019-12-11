package com.example.moviecatalogueega.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * La ilaha illAllah Muhammadur Rasulullahh.
 */

public class SelisihWaktu {
    static SimpleDateFormat formatjaml= new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat formatjam = new SimpleDateFormat("HH:mm:ss");

    public static long tanggal(String firstDate, String secondDate) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));

        long daysDiff = 0;
        long timeDiff = 0;
        try {
            Date date1 = formatjaml.parse(firstDate);
            Date date2 = formatjaml.parse(secondDate);
            timeDiff = date2.getTime() - date1.getTime();
            daysDiff = timeDiff / (1000 * 60 * 60 * 24);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return daysDiff;
    }

    public static long jam(String d1, String d2) {
        long diff = 0;
        long diffHours = 123;

        try {
            Date jamawl = formatjam.parse(d1);
            Date jamakhr = formatjam.parse(d2);

            //in milliseconds
            diff = jamakhr.getTime() - jamawl.getTime();
            diffHours = diff / (60 * 60 * 1000) % 24;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diffHours;
    }
}
