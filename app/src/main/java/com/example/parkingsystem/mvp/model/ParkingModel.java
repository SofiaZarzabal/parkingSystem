package com.example.parkingsystem.mvp.model;

import com.example.parkingsystem.mvp.contract.ParkingContract;

public class ParkingModel implements ParkingContract.ParkingModel {

    private int parkingLots;

    public int getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(int parkingLots) {
        this.parkingLots = parkingLots;
    }
}
