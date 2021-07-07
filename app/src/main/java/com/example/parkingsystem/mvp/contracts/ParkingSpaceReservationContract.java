package com.example.parkingsystem.mvp.contracts;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.Button;
import com.example.parkingsystem.entity.Reservation;
import com.example.parkingsystem.utils.ReservationVerificationResult;
import java.util.Calendar;

public interface ParkingSpaceReservationContract {

    interface ParkingSpaceReservationPresenter {
        void onButtonParkingSpaceReservationPickerPressed(DatePickerDialog.OnDateSetListener listener);

        void onButtonParkingSpaceReservationSavePressed();

        void onDateSetPressed(int year, int month, int day, TimePickerDialog.OnTimeSetListener timeListener);

        void onTimeSetPressed(int hour, int time);
    }

    interface ParkingSpaceReservationView {
        void showDatePickerDialog(DatePickerDialog.OnDateSetListener listener);

        void showDateAndTimeStart(String date, String time);

        void showDateAndTimeEnd(String date, String time);

        void showTimePickerDialog(TimePickerDialog.OnTimeSetListener timeListener);

        void enableButtonEnd();

        void showSaveDone();

        int getParkingSpace();

        int getSecurityCode();

        void showMissingFieldMessage(ReservationVerificationResult reservationVerificationResult);

        Button getButtonPickerStart();

    }

    interface ParkingSpaceReservationModel {

        void makeReservation();

        ReservationVerificationResult checkFields(Reservation reservation);

        Reservation getReservation();

        void completeReservationInfo(int parkingSpace, int securityCode);

        boolean getDateStartButtonPressed();

        void setDateStartButtonPressed(boolean isDateStartButtonPressed);

        boolean isPressed(Button btn);
    }
}
