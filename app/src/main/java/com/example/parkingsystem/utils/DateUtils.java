package com.example.parkingsystem.utils;

import android.icu.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Calendar convertToCalendar(String sDate, SimpleDateFormat format) {
        Date date = new Date();
        try {
            date = format.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String convertToString(Calendar calendar, SimpleDateFormat format) {
        return format.format(calendar.getTime());
    }
}
