package com.example.parkingsystem.mvp.presenter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import com.example.parkingsystem.mvp.contracts.ParkingSpaceReservationContract;
import com.example.parkingsystem.mvp.model.ParkingSpaceReservationModel;
import com.example.parkingsystem.mvp.view.ParkingSpaceReservationView;

public class ParkingSpaceReservationPresenter implements ParkingSpaceReservationContract.ParkingSpaceReservationPresenter {
    private ParkingSpaceReservationContract.ParkingSpaceReservationView view;
    private ParkingSpaceReservationContract.ParkingSpaceReservationModel model;

    public ParkingSpaceReservationPresenter(ParkingSpaceReservationModel model, ParkingSpaceReservationView view) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onButtonParkingSpaceReservationPickerPressed(DatePickerDialog.OnDateSetListener listener) {
        view.showDatePickerDialog(listener);
    }

    @Override
    public void onButtonParkingSpaceReservationSavePressed() {
        model.makeReservation(view.getParkingSpace(), view.getSecurityCode());
        view.showSaveDone();
    }

    @Override
    public void onDateSetPressed(String date, TimePickerDialog.OnTimeSetListener listener) {
        if (model.getDateStart() == null) {
            model.setDateStart(date);
        } else {
            model.setDateEnd(date);
        }
        view.showTimePickerDialog(listener);
    }

    @Override
    public void onTimeSetPressed(String time) {
        if (model.getTimeStart() == null) {
            model.setTimeStart(time);
            view.enableButtonEnd();
            view.showDateAndTimeStart(model.getDateStart(), model.getTimeStart());
        } else {
            model.setTimeEnd(time);
            view.showDateAndTimeEnd(model.getDateEnd(), model.getTimeEnd());
        }
    }
}
