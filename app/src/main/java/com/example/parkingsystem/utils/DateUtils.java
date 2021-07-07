package com.example.parkingsystem.utils;

import android.icu.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Calendar convertToCalendar(String sDate, SimpleDateFormat sdf) {
        Date date = new Date();
        try {
            date = sdf.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String convertToString(Calendar calendar, SimpleDateFormat sdf) {
        return sdf.format(calendar.getTime());
    }
}
