package com.example.parkingsystem.mvp.contract;

public interface ParkingContract {

    interface ParkingPresenter{
        void onButtonMainSelectParkingPressed();
    }

    interface ParkingView{
        void showParkingSpaces(int parkingLots);
    }

    interface ParkingModel{
        int getParkingLots();
        void setParkingLots(int parkingLots);
    }
}
