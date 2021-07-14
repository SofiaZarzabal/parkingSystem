package com.example.parkingsystem.mvp.model;

import android.icu.text.SimpleDateFormat;
import com.example.parkingsystem.database.ParkingSpaceReservationDB;
import com.example.parkingsystem.entity.Reservation;
import com.example.parkingsystem.mvp.contracts.ParkingSpaceReservationContract;
import com.example.parkingsystem.utils.Constants;
import com.example.parkingsystem.utils.DateUtils;
import com.example.parkingsystem.utils.ReservationVerification;
import com.example.parkingsystem.utils.ReservationVerificationResult;
import java.util.Calendar;
import java.util.Locale;

public class ParkingSpaceReservationModel implements ParkingSpaceReservationContract.ParkingSpaceReservationModel {
    private Reservation reservation = new Reservation();
    private boolean isDateStartButtonPressed = false;
    private ParkingSpaceReservationDB database;
    private ReservationVerification verification;

    public ParkingSpaceReservationModel(ParkingSpaceReservationDB database) {
        this.database = database;
        verification = new ReservationVerification(database);
    }

    @Override
    public Calendar getDateStart() {
        return reservation.getDateStart();
    }

    @Override
    public Calendar getTimeStart() {
        return reservation.getTimeStart();
    }

    @Override
    public Calendar getDateEnd() {
        return reservation.getDateEnd();
    }

    @Override
    public Calendar getTimeEnd() {
        return reservation.getTimeEnd();
    }

    @Override
    public void setDateStart(Calendar dateStart) {
        reservation.setDateStart(dateStart);
    }

    @Override
    public void setTimeStart(Calendar timeStart) {
        reservation.setTimeStart(timeStart);
    }

    @Override
    public void setDateEnd(Calendar dateEnd) {
        reservation.setDateEnd(dateEnd);
    }

    @Override
    public void setTimeEnd(Calendar timeEnd) {
        reservation.setTimeEnd(timeEnd);
    }

    @Override
    public Calendar convertToCalendar(String sDate, String FORMAT) {
        SimpleDateFormat formatDate = new SimpleDateFormat(FORMAT, Locale.getDefault());
        return DateUtils.convertToCalendar(sDate, formatDate);
    }

    @Override
    public boolean getDateStartButtonPressed() {
        return isDateStartButtonPressed;
    }

    @Override
    public void setDateStartButtonPressed(boolean isDateStartButtonPressed) {
        this.isDateStartButtonPressed = isDateStartButtonPressed;
    }

    @Override
    public Reservation getReservation() {
        return reservation;
    }

    @Override
    public void makeReservation(Reservation reservation) {
        database.addReservation(reservation);
    }

    @Override
    public int releaseReservations() {
        return database.releasePastReservations();
    }

    @Override
    public void completeReservationInfo(int parkingSpace, int securityCode) {
        reservation.setParkingSpace(parkingSpace);
        reservation.setSecurityCode(securityCode);
    }

    @Override
    public ReservationVerificationResult checkFields() {
        if (getReservation().getDateStart() == null) {
            return ReservationVerificationResult.MISSING_DATE_START;
        }
        if (getReservation().getTimeStart() == null) {
            return ReservationVerificationResult.MISSING_TIME_START;
        }
        if (getReservation().getDateEnd() == null) {
            return ReservationVerificationResult.MISSING_DATE_END;
        }
        if (getReservation().getTimeEnd() == null) {
            return ReservationVerificationResult.MISSING_TIME_END;
        }
        if (getReservation().getParkingSpace() == Constants.NUMBER_MINUS_ONE) {
            return ReservationVerificationResult.MISSING_PARKING_SPACE;
        }
        if (getReservation().getSecurityCode() == Constants.NUMBER_MINUS_ONE) {
            return ReservationVerificationResult.MISSING_SECURITY_CODE;
        }
        if (!verification.canBeReserved(getReservation())) {
            return ReservationVerificationResult.RESERVATION_OVERLAPPING;
        }
        return ReservationVerificationResult.SUCCESS;
    }
}
