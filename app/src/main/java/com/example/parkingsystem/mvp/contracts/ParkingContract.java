package com.example.parkingsystem.mvp.contracts;

public interface ParkingContract {

    interface ParkingPresenter {
        void onButtonMainSelectParkingPressed();

        void onButtonDialogParkingConfirmPressed(int parkingSpaces);

        void onButtonMainBookParkingLotPressed();
    }

    interface ParkingView {
        void showParkingSpaces(int parkingSpaces);

        void showParkingAlertDialog();

        void showParkingSpaceReservation();
    }

    interface ParkingModel {
        void setParkingSpaces(int parkingSpaces);

        int getParkingSpaces();
    }
}
