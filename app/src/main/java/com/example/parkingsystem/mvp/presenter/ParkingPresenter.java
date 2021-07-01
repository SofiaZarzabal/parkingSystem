package com.example.parkingsystem.mvp.presenter;

import com.example.parkingsystem.mvp.contracts.ParkingContract;

public class ParkingPresenter implements ParkingContract.ParkingPresenter {

    private ParkingContract.ParkingModel model;
    private ParkingContract.ParkingView view;

    public ParkingPresenter(ParkingContract.ParkingModel model, ParkingContract.ParkingView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onButtonMainSelectParkingPressed() {
        view.showParkingAlertDialog();
    }

    @Override
    public void onButtonDialogParkingConfirmPressed(int parkingSpaces) {
        model.setParkingSpaces(parkingSpaces);
        view.showParkingSpaces(model.getParkingSpaces());
    }

    @Override
    public void onButtonMainBookParkingLotPressed() {
        view.showParkingSpaceReservation();
    }
}
