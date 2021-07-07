package com.example.parkingsystem.database;

import com.example.parkingsystem.entity.Reservation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingSpaceReservationDB {
    private static ParkingSpaceReservationDB database = null;
    HashMap<Integer, List<Reservation>> hashReservation = new HashMap<>();

    private ParkingSpaceReservationDB() {

    }

    public static ParkingSpaceReservationDB getInstance() {
        if (database == null) {
            database = new ParkingSpaceReservationDB();
        }
        return database;
    }

    public HashMap<Integer, List<Reservation>> getHash() {
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
}
