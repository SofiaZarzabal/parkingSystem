package com.example.parkingsystem.entity;

import android.icu.text.SimpleDateFormat;
import com.example.parkingsystem.utils.Constants;
import com.example.parkingsystem.utils.DateUtils;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Reservation {
    private Calendar dateStart;
    private Calendar timeStart;
    private Calendar dateEnd;
    private Calendar timeEnd;
    private int parkingSpace;
    private int securityCode;
    private final SimpleDateFormat formatDate = new SimpleDateFormat(Constants.FORMAT_DATE, Locale.getDefault());
    private final SimpleDateFormat formatTime = new SimpleDateFormat(Constants.FORMAT_TIME, Locale.getDefault());

    public Reservation() {
        this.parkingSpace = Constants.NUMBER_MINUS_ONE;
        this.securityCode = Constants.NUMBER_MINUS_ONE;
    }

    public String getFormattedDate(Calendar date) {
        return DateUtils.convertToString(date, formatDate);
    }

    public String getFormattedTime(Calendar time) {
        return DateUtils.convertToString(time, formatTime);
    }

    public Calendar getDateAndTimeStart() {
        return new GregorianCalendar(dateStart.get(Calendar.YEAR), dateStart.get(Calendar.MONTH), dateStart.get(Calendar.DAY_OF_MONTH), timeStart.get(Calendar.HOUR_OF_DAY), timeStart.get(Calendar.MINUTE));
    }

    public Calendar getDateAndTimeEnd() {
        return new GregorianCalendar(dateEnd.get(Calendar.YEAR), dateEnd.get(Calendar.MONTH), dateEnd.get(Calendar.DAY_OF_MONTH), timeEnd.get(Calendar.HOUR_OF_DAY), timeEnd.get(Calendar.MINUTE));
    }

    public Calendar getDateStart() {
        return dateStart;
    }

    public void setDateStart(Calendar dateStart) {
        this.dateStart = dateStart;
    }

    public Calendar getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Calendar timeStart) {
        this.timeStart = timeStart;
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Calendar dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Calendar getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Calendar timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(int parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
}
