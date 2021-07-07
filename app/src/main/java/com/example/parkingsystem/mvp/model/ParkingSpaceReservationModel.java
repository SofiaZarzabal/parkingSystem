package com.example.parkingsystem.mvp.model;

import android.widget.Button;
import com.example.parkingsystem.database.ParkingSpaceReservationDB;
import com.example.parkingsystem.entity.Reservation;
import com.example.parkingsystem.mvp.contracts.ParkingSpaceReservationContract;
import com.example.parkingsystem.utils.Constants;
import com.example.parkingsystem.utils.ReservationVerification;
import com.example.parkingsystem.utils.ReservationVerificationResult;

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
    public boolean getDateStartButtonPressed() {
        return isDateStartButtonPressed;
    }

    @Override
    public void setDateStartButtonPressed(boolean isDateStartButtonPressed) {
        this.isDateStartButtonPressed = isDateStartButtonPressed;
    }

    @Override
    public boolean isPressed(Button btn) {
        return btn.isPressed();
    }

    @Override
    public Reservation getReservation() {
        return reservation;
    }

    @Override
    public void completeReservationInfo(int parkingSpace, int securityCode) {
        reservation.setParkingSpace(parkingSpace);
        reservation.setSecurityCode(securityCode);
    }

    @Override
    public void makeReservation() {
        database.addReservation(reservation);
    }

    @Override
    public ReservationVerificationResult checkFields(Reservation reservation) {
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
        if (!verification.canBeReserved(reservation)) {
            return ReservationVerificationResult.RESERVATION_OVERLAPPING;
        }
        return ReservationVerificationResult.SUCCESS;

    }
}
