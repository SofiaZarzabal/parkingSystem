package com.example.parkingsystem.mvp.model;

import com.example.parkingsystem.mvp.contracts.ParkingSpaceReservationContract;

public class ParkingSpaceReservationModel implements ParkingSpaceReservationContract.ParkingSpaceReservationModel {
    private String dateStart;
    private String timeStart;
    private String dateEnd;
    private String timeEnd;

    @Override
    public void setDateStart(String date) {
        this.dateStart = date;
    }

    @Override
    public void setDateEnd(String date) {
        this.dateEnd = date;
    }

    @Override
    public String getDateStart() {
        return dateStart;
    }

    @Override
    public String getDateEnd() {
        return dateEnd;
    }

    @Override
    public void setTimeStart(String time) {
        this.timeStart = time;
    }

    @Override
    public void setTimeEnd(String time) {
        this.timeEnd = time;
    }

    @Override
    public String getTimeStart() {
        return timeStart;
    }

    @Override
    public String getTimeEnd() {
        return timeEnd;
    }
}
