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
        model.setDateStartButtonPressed(view.getButtonPickerStart().isPressed());
        view.showDatePickerDialog(listener);
    }

    @Override
    public void onButtonParkingSpaceReservationSavePressed() {
        view.showReleasedPastReservations(model.releaseReservations());
        int parkingSpace = view.getParkingSpace().isEmpty() ? Constants.NUMBER_MINUS_ONE : Integer.parseInt(view.getParkingSpace());
        int securityCode = view.getSecurityCode().isEmpty() ? Constants.NUMBER_MINUS_ONE : Integer.parseInt(view.getSecurityCode());
        model.completeReservationInfo(parkingSpace, securityCode);
        ReservationVerificationResult reservationVerificationResult = model.checkFields();
        switch (reservationVerificationResult) {
            case MISSING_DATE_START:
                view.showMissingDateStart();
                break;
            case MISSING_TIME_START:
                view.showMissingTimeStart();
                break;
            case MISSING_DATE_END:
                view.showMissingDateEnd();
                break;
            case MISSING_TIME_END:
                view.showMissingTimeEnd();
                break;
            case MISSING_PARKING_SPACE:
                view.showMissingParkingSpace();
                break;
            case MISSING_SECURITY_CODE:
                view.showMissingSecurityCode();
                break;
            case RESERVATION_OVERLAPPING:
                view.showReservationOverlapping();
                break;
            case SUCCESS:
                model.makeReservation(model.getReservation());
                view.showSaveDone();
                break;
        }
    }

    @Override
    public void onDateSetPressed(int year, int month, int day, TimePickerDialog.OnTimeSetListener listener) {
        String sDate = day + Constants.SLASH + (month + Constants.ONE) + Constants.SLASH + year;
        SimpleDateFormat formatDate = new SimpleDateFormat(Constants.FORMAT_DATE, Locale.getDefault());
        Calendar date = DateUtils.convertToCalendar(sDate, formatDate);
        if (model.getDateStartButtonPressed()) {
            model.setDateStart(date);
        } else {
            model.setDateEnd(date);
        }
        view.showTimePickerDialog(listener);
    }

    @Override
    public void onTimeSetPressed(int hour, int minute) {
        String sTime = hour + Constants.TWO_POINTS + minute;
        SimpleDateFormat formatTime = new SimpleDateFormat(Constants.FORMAT_TIME, Locale.getDefault());
        Calendar time = DateUtils.convertToCalendar(sTime, formatTime);
        if (model.getDateStartButtonPressed()) {
            model.setTimeStart(time);
            view.enableButtonEnd();
            view.showDateAndTimeStart(model.getReservation().getFormattedDate(model.getDateStart()), model.getReservation().getFormattedTime(model.getTimeStart()));
        } else {
            model.setTimeEnd(time);
            view.showDateAndTimeEnd(model.getReservation().getFormattedDate(model.getDateEnd()), model.getReservation().getFormattedTime(model.getTimeEnd()));
        }
    }

    @Override
    public void onButtonParkingSpaceReservationDeletePressed() {
        view.showReleasedPastReservations(model.releaseReservations());
    }
}
