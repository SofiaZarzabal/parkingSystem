package com.example.parkingsystem.mvp.presenter;

import com.example.parkingsystem.mvp.contract.ParkingContract;

public class ParkingPresenter implements ParkingContract.ParkingPresenter {

    private ParkingContract.ParkingModel model;
    private ParkingContract.ParkingView view;

    public ParkingPresenter(ParkingContract.ParkingModel model, ParkingContract.ParkingView view) {
        this.model = model;
        this.view = view;
    }

    public void onButtonMainSelectParkingPressed() {
        model.setParkingLots(10);
        view.showParkingSpaces(model.getParkingLots());
    }
}
