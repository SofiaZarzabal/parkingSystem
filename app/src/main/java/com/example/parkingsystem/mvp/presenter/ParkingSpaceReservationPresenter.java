package com.example.parkingsystem.mvp.presenter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import com.example.parkingsystem.mvp.contracts.ParkingSpaceReservationContract;
import com.example.parkingsystem.mvp.model.ParkingSpaceReservationModel;
import com.example.parkingsystem.mvp.view.ParkingSpaceReservationView;
import com.example.parkingsystem.utils.Constants;
import com.example.parkingsystem.utils.DateUtils;
import com.example.parkingsystem.utils.ReservationVerificationResult;
import java.util.Calendar;
import java.util.Locale;

public class ParkingSpaceReservationPresenter implements ParkingSpaceReservationContract.ParkingSpaceReservationPresenter {
    private ParkingSpaceReservationContract.ParkingSpaceReservationView view;
    private ParkingSpaceReservationContract.ParkingSpaceReservationModel model;

    public ParkingSpaceReservationPresenter(ParkingSpaceReservationModel model, ParkingSpaceReservationView view) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onButtonParkingSpaceReservationPickerPressed(DatePickerDialog.OnDateSetListener listener) {
        if (model.isPressed(view.getButtonPickerStart())) {
            model.setDateStartButtonPressed(true);
        } else {
            model.setDateStartButtonPressed(false);
        }
        view.showDatePickerDialog(listener);
    }

    @Override
    public void onButtonParkingSpaceReservationSavePressed() {
        model.completeReservationInfo(view.getParkingSpace(), view.getSecurityCode());
        ReservationVerificationResult reservationVerificationResult = model.checkFields(model.getReservation());
        if (reservationVerificationResult == ReservationVerificationResult.SUCCESS) {
            model.makeReservation();
            view.showSaveDone();
        } else {
            view.showMissingFieldMessage(reservationVerificationResult);
        }
    }

    @Override
    public void onDateSetPressed(int year, int month, int day, TimePickerDialog.OnTimeSetListener listener) {
        String sDate = day + Constants.SLASH + (month + Constants.ONE) + Constants.SLASH + year;
        SimpleDateFormat formatDate = new SimpleDateFormat(Constants.FORMAT_DATE, Locale.getDefault());
        Calendar date = DateUtils.convertToCalendar(sDate, formatDate);
        if (model.getDateStartButtonPressed()) {
            model.getReservation().setDateStart(date);
        } else {
            model.getReservation().setDateEnd(date);
        }
        view.showTimePickerDialog(listener);
    }


    @Override
    public void onTimeSetPressed(int hour, int minute) {
        String sTime = hour + Constants.TWO_POINTS + minute;
        SimpleDateFormat formatTime = new SimpleDateFormat(Constants.FORMAT_TIME, Locale.getDefault());
        Calendar time = DateUtils.convertToCalendar(sTime, formatTime);
        if (model.getDateStartButtonPressed()) {
            model.getReservation().setTimeStart(time);
            view.enableButtonEnd();
            view.showDateAndTimeStart(model.getReservation().getFormattedDate(model.getReservation().getDateStart()), model.getReservation().getFormattedTime(model.getReservation().getTimeStart()));
        } else {
            model.getReservation().setTimeEnd(time);
            view.showDateAndTimeEnd(model.getReservation().getFormattedDate(model.getReservation().getDateEnd()), model.getReservation().getFormattedTime(model.getReservation().getTimeEnd()));
        }
    }
}
