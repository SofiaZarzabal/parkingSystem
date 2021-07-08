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

        void onButtonParkingSpaceReservationDeletePressed();
    }

    interface ParkingSpaceReservationView {
        void showDatePickerDialog(DatePickerDialog.OnDateSetListener listener);

        void showDateAndTimeStart(String date, String time);

        void showDateAndTimeEnd(String date, String time);

        void showTimePickerDialog(TimePickerDialog.OnTimeSetListener timeListener);

        void enableButtonEnd();

        void showSaveDone();

        String getParkingSpace();

        String getSecurityCode();

        void showMissingDateStart();

        void showMissingTimeStart();

        void showMissingDateEnd();

        void showMissingTimeEnd();

        void showMissingParkingSpace();

        void showMissingSecurityCode();

        void showReservationOverlapping();

        Button getButtonPickerStart();

        void showReleasedPastReservations(int amountReservations);

    }

    interface ParkingSpaceReservationModel {

        ReservationVerificationResult checkFields();

        Reservation getReservation();

        void completeReservationInfo(int parkingSpace, int securityCode);

        boolean getDateStartButtonPressed();

        void setDateStartButtonPressed(boolean isDateStartButtonPressed);

        void makeReservation(Reservation reservation);

        int releaseReservations();

        Calendar getDateStart();

        Calendar getTimeStart();

        Calendar getDateEnd();

        Calendar getTimeEnd();

        void setDateStart(Calendar dateStart);

        void setTimeStart(Calendar timeStart);

        void setDateEnd(Calendar dateEnd);

        void setTimeEnd(Calendar timeEnd);

    }
}
