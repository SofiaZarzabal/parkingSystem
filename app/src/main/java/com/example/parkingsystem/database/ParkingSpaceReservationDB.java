package com.example.parkingsystem.database;

import com.example.parkingsystem.entity.Reservation;
import com.example.parkingsystem.utils.Constants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingSpaceReservationDB {
    private static ParkingSpaceReservationDB database = null;
    Map<Integer, List<Reservation>> hashReservation = new HashMap<>();

    private ParkingSpaceReservationDB() {

    }

    public static ParkingSpaceReservationDB getInstance() {
        if (database == null) {
            database = new ParkingSpaceReservationDB();
        }
        return database;
    }

    public Map<Integer, List<Reservation>> getHash() {
        return hashReservation;
    }

    public void addReservation(Reservation reservation) {
        List<Reservation> reservations = new ArrayList<>();
        int parkingSpace = reservation.getParkingSpace();
        if (hashReservation.containsKey(parkingSpace)) {
            reservations = hashReservation.get(parkingSpace);
        }
        reservations.add(reservation);
        hashReservation.put(parkingSpace, reservations);
    }

    public int releasePastReservations() {
        int reservationsReleased = Constants.ZERO;
        Calendar currentCalendar = Calendar.getInstance();
        for (List<Reservation> reservationList : hashReservation.values()) {
            for (int i = Constants.ZERO; i < reservationList.size(); i++) {
                if (reservationList.get(i).getDateAndTimeEnd().before(currentCalendar)) {
                    reservationList.remove(reservationList.get(i));
                    reservationsReleased++;
                }
            }
        }
        return reservationsReleased;
    }
}
