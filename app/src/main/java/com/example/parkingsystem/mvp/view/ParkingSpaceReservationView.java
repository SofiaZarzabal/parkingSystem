package com.example.parkingsystem.mvp.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.Toast;
import com.example.parkingsystem.R;
import com.example.parkingsystem.databinding.ActivityReservationSpaceParkingBinding;
import com.example.parkingsystem.mvp.contracts.ParkingSpaceReservationContract;
import com.example.parkingsystem.mvp.view.base.ActivityView;
import java.util.Calendar;

public class ParkingSpaceReservationView extends ActivityView implements ParkingSpaceReservationContract.ParkingSpaceReservationView {
    private ActivityReservationSpaceParkingBinding binding;

    public ParkingSpaceReservationView(Activity activity, ActivityReservationSpaceParkingBinding binding) {
        super(activity);
        this.binding = binding;
    }

    @Override
    public boolean getButtonPickerStart() {
        return binding.buttonParkingSpaceReservationPickerBegin.isPressed();
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
    public String getParkingSpace() {
        return binding.inputParkingSpaceReservationPlace.getText().toString();
    }

    @Override
    public String getSecurityCode() {
        return binding.inputParkingSpaceReservationCode.getText().toString();
    }

    @Override
    public void showSaveDone() {
        Context context = getContext();
        Activity activity = getActivity();
        if (context != null && activity != null) {
            showMessage(context, context.getString(R.string.toast_parking_space_reservation_save));
            activity.finish();
        }
    }

    @Override
    public void showReleasedPastReservations(int amountReservations) {
        Context context = getContext();
        if (context != null) {
            showMessage(context, context.getString(R.string.toast_parking_space_reservation_amount_reserves_released, amountReservations));
        }
    }

    @Override
    public void showMissingDateStart() {
        Context context = getContext();
        if (context != null) {
            showMessage(context, context.getString(R.string.toast_parking_space_reservation_missing_date_start));
        }
    }

    @Override
    public void showMissingTimeStart() {
        Context context = getContext();
        if (context != null) {
            showMessage(context, context.getString(R.string.toast_parking_space_reservation_missing_time_start));
        }
    }

    @Override
    public void showMissingDateEnd() {
        Context context = getContext();
        if (context != null) {
            showMessage(context, context.getString(R.string.toast_parking_space_reservation_missing_date_end));
        }
    }

    @Override
    public void showMissingTimeEnd() {
        Context context = getContext();
        if (context != null) {
            showMessage(context, context.getString(R.string.toast_parking_space_reservation_missing_time_end));
        }
    }

    @Override
    public void showMissingParkingSpace() {
        Context context = getContext();
        if (context != null) {
            showMessage(context, context.getString(R.string.toast_parking_space_reservation_missing_place));
        }
    }

    @Override
    public void showMissingSecurityCode() {
        Context context = getContext();
        if (context != null) {
            showMessage(context, context.getString(R.string.toast_parking_space_reservation_missing_security_code));
        }
    }

    @Override
    public void showReservationOverlapping() {
        Context context = getContext();
        if (context != null) {
            showMessage(context, context.getString(R.string.toast_parking_space_reservation_reservation_overlapping));
        }
    }
}
