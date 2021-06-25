package com.example.parkingsystem.mvp.presenter;

import com.example.parkingsystem.listener.ConfigureParkingDialogListener;
import com.example.parkingsystem.mvp.contracts.ConfigureParkingDialogContract;

public class ConfigureParkingDialogPresenter implements ConfigureParkingDialogContract.ConfigureParkingDialogPresenter {
    private ConfigureParkingDialogContract.ConfigureParkingDialogView view;

    public ConfigureParkingDialogPresenter(ConfigureParkingDialogContract.ConfigureParkingDialogView view) {
        this.view = view;
    }

    @Override
    public void onButtonDialogParkingConfirmPressed(ConfigureParkingDialogListener listener) {
        String parkingSpaces = view.getParkingSpaces();
        if (!parkingSpaces.isEmpty()) {
            int intParkingSpaces = Integer.parseInt(parkingSpaces);
            view.closeDialog();
            view.showParkingSpaces(intParkingSpaces, listener);
        } else {
            view.showToastEmptyInput();
        }
    }

    @Override
    public void onButtonDialogParkingCancelPressed() {
        view.closeDialog();
    }
}
