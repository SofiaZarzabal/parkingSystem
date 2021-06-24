package com.example.parkingsystem.mvp.contracts;

import com.example.parkingsystem.listener.ConfigureParkingDialogListener;

public interface ConfigureParkingDialogContract {

    interface ConfigureParkingDialogPresenter {
        void onButtonDialogParkingConfirmPressed(ConfigureParkingDialogListener listener);

        void onButtonDialogParkingCancelPressed();
    }

    interface ConfigureParkingDialogView {
        String getParkingSpaces();

        void closeDialog();

        void showToastEmptyInput();

        void showParkingSpaces(int intParkingSpaces, ConfigureParkingDialogListener listener);
    }
}
