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
        if (context != null && activity != null) {
            Toast.makeText(context, context.getString(R.string.toast_parking_space_reservation_save), Toast.LENGTH_SHORT).show();
            activity.finish();
        }
    }
}
