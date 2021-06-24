package com.example.parkingsystem.mvp.model;

import com.example.parkingsystem.mvp.contracts.ParkingContract;

public class ParkingModel implements ParkingContract.ParkingModel {
    private int parkingSpaces;

    @Override
    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    @Override
    public int getParkingSpaces() {
        return parkingSpaces;
    }
}
