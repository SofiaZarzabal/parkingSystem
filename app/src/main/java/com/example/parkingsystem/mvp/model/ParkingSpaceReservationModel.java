package com.example.parkingsystem.mvp.model;

import com.example.parkingsystem.database.ParkingSpaceReservationDB;
import com.example.parkingsystem.entity.Reservation;
import com.example.parkingsystem.mvp.contracts.ParkingSpaceReservationContract;

public class ParkingSpaceReservationModel implements ParkingSpaceReservationContract.ParkingSpaceReservationModel {
    private Reservation reservation = new Reservation();
    private ParkingSpaceReservationDB database;

    public ParkingSpaceReservationModel(ParkingSpaceReservationDB database) {
        this.database = database;
    }

    @Override
    public String getDateStart() {
        return this.reservation.getDateStart();
    }

    @Override
    public void setDateStart(String date) {
        this.reservation.setDateStart(date);
    }

    @Override
    public String getTimeStart() {
        return reservation.getTimeStart();
    }

    @Override
    public void setTimeStart(String time) {
        this.reservation.setTimeStart(time);
    }

    @Override
    public String getDateEnd() {
        return reservation.getDateEnd();
    }

    @Override
    public void setDateEnd(String date) {
        this.reservation.setDateEnd(date);
    }

    @Override
    public String getTimeEnd() {
        return reservation.getTimeEnd();
    }

    @Override
    public void setTimeEnd(String time) {
        this.reservation.setTimeEnd(time);
    }

    @Override
    public void makeReservation(int parkingSpace, int securityCode) {
        reservation.setParkingSpace(parkingSpace);
        reservation.setSecurityCode(securityCode);
        database.addReservation(reservation);
    }
}
