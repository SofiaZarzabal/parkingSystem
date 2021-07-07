package com.example.parkingsystem.mvp.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.Toast;
import com.example.parkingsystem.R;
import com.example.parkingsystem.databinding.ActivityReservationSpaceParkingBinding;
import com.example.parkingsystem.mvp.contracts.ParkingSpaceReservationContract;
import com.example.parkingsystem.mvp.view.base.ActivityView;
import com.example.parkingsystem.utils.ReservationVerificationResult;
import java.util.Calendar;

public class ParkingSpaceReservationView extends ActivityView implements ParkingSpaceReservationContract.ParkingSpaceReservationView {
    private ActivityReservationSpaceParkingBinding binding;

    public ParkingSpaceReservationView(Activity activity, ActivityReservationSpaceParkingBinding binding) {
        super(activity);
        this.binding = binding;
    }

    @Override
    public Button getButtonPickerStart() {
        return binding.buttonParkingSpaceReservationPickerBegin;
    }

    @Override
    public void showDatePickerDialog(DatePickerDialog.OnDateSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        Context context = getContext();
        if (context != null) {
            DatePickerDialog datePicker = new DatePickerDialog(context, listener, year, month, day);
            datePicker.show();
        }
    }

    @Override
    public void showDateAndTimeStart(String date, String time) {
        Context context = getContext();
        if (context != null) {
            binding.textParkingSpaceReservationPickerBegin.setText(context.getString(R.string.text_parking_space_reservation_picker, date, time));
        }
    }

    @Override
    public void showDateAndTimeEnd(String date, String time) {
        Context context = getContext();
        if (context != null) {
            binding.textParkingSpaceReservationPickerEnd.setText(context.getString(R.string.text_parking_space_reservation_picker, date, time));
        }
    }

    @Override
    public void showTimePickerDialog(TimePickerDialog.OnTimeSetListener listener) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        Context context = getContext();
        if (context != null) {
            TimePickerDialog timePicker = new TimePickerDialog(context, listener, hour, minute, false);
            timePicker.show();
        }
    }

    private void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enableButtonEnd() {
        binding.buttonParkingSpaceReservationPickerEnd.setEnabled(true);
    }

    @Override
    public int getParkingSpace() {
        return Integer.parseInt(binding.inputParkingSpaceReservationPlace.getText().toString());
    }

    @Override
    public int getSecurityCode() {
        return Integer.parseInt(binding.inputParkingSpaceReservationCode.getText().toString());
    }

    @Override
    public void showSaveDone() {
        Context context = getContext();
        Activity activity = getActivity();
        String message = null;
        if (context != null && activity != null) {
            message = context.getString(R.string.toast_parking_space_reservation_save);
            showMessage(context, message);
            activity.finish();
        }
    }

    @Override
    public void showMissingFieldMessage(ReservationVerificationResult reservationVerificationResult) {
        Context context = getContext();
        String message = null;
        if (context != null) {
            switch (reservationVerificationResult) {
                case MISSING_DATE_START:
                    message = context.getString(R.string.toast_parking_space_reservation_missing_date_start);
                    break;
                case MISSING_TIME_START:
                    message = context.getString(R.string.toast_parking_space_reservation_missing_time_start);
                    break;
                case MISSING_DATE_END:
                    message = context.getString(R.string.toast_parking_space_reservation_missing_date_end);
                    break;
                case MISSING_TIME_END:
                    message = context.getString(R.string.toast_parking_space_reservation_missing_time_end);
                    break;
                case MISSING_PARKING_SPACE:
                    message = context.getString(R.string.toast_parking_space_reservation_missing_place);
                    break;
                case MISSING_SECURITY_CODE:
                    message = context.getString(R.string.toast_parking_space_reservation_missing_security_code);
                    break;
                case RESERVATION_OVERLAPPING:
                    message = context.getString(R.string.toast_parking_space_reservation_reservation_overlapping);
            }
        }
        showMessage(context, message);
    }
}
