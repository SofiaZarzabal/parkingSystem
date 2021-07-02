package com.example.parkingsystem.mvp.contracts;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

public interface ParkingSpaceReservationContract {

    interface ParkingSpaceReservationPresenter {
        void onButtonParkingSpaceReservationPickerPressed(DatePickerDialog.OnDateSetListener listener);

        void onButtonParkingSpaceReservationSavePressed();

        void onDateSetPressed(String date, TimePickerDialog.OnTimeSetListener timeListener);

        void onTimeSetPressed(String time);
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
    }

    interface ParkingSpaceReservationModel {

        void setDateStart(String date);

        String getDateStart();

        void setTimeStart(String time);

        String getTimeStart();

        void setDateEnd(String date);

        String getDateEnd();

        void setTimeEnd(String time);

        String getTimeEnd();

        void makeReservation(int parkingSpace, int securityCode);
    }
}
