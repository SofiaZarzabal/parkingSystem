package com.example.parkingsystem.utils;

import com.example.parkingsystem.database.ParkingSpaceReservationDB;
import com.example.parkingsystem.entity.Reservation;
import java.util.Calendar;
import java.util.List;

public class ReservationVerification {
    private ParkingSpaceReservationDB database;

    public ReservationVerification(ParkingSpaceReservationDB database) {
        this.database = database;
    }

    public boolean canBeReserved(Reservation reservation) {
        int parkingSpace = reservation.getParkingSpace();
        if (database.getHash().containsKey(parkingSpace)) {
            List<Reservation> reservations = database.getHash().get(parkingSpace);
            for (Reservation r : reservations) {
                if (isOverlap(reservation, r)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isOverlap(Reservation reservation, Reservation storedReservation) {
        Calendar reservationStart = reservation.getDateAndTimeStart();
        Calendar reservationEnd = reservation.getDateAndTimeEnd();
        Calendar storedReservationStart = storedReservation.getDateAndTimeStart();
        Calendar storedReservationEnd = storedReservation.getDateAndTimeEnd();
        return reservationStart.after(storedReservationStart) && reservationStart.before(storedReservationEnd) || reservationEnd.after(storedReservationStart) && reservationEnd.before(storedReservationEnd);
    }
}
