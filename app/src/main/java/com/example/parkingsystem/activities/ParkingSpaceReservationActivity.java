package com.example.parkingsystem.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import com.example.parkingsystem.databinding.ActivityReservationSpaceParkingBinding;
import com.example.parkingsystem.mvp.contracts.ParkingSpaceReservationContract;
import com.example.parkingsystem.mvp.model.ParkingSpaceReservationModel;
import com.example.parkingsystem.mvp.presenter.ParkingSpaceReservationPresenter;
import com.example.parkingsystem.mvp.view.ParkingSpaceReservationView;

public class ParkingSpaceReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private ActivityReservationSpaceParkingBinding binding;
    private ParkingSpaceReservationContract.ParkingSpaceReservationPresenter presenter;
    private final String SLASH = "/";
    private final String TWO_POINTS = ":";
    private final int ONE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReservationSpaceParkingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ParkingSpaceReservationPresenter(new ParkingSpaceReservationModel(), new ParkingSpaceReservationView(this, binding));
        setListener();
    }

    public void setListener() {
        binding.buttonParkingSpaceReservationPickerBegin.setOnClickListener(view -> presenter.onButtonParkingSpaceReservationPickerPressed(this));
        binding.buttonParkingSpaceReservationPickerEnd.setOnClickListener(view -> presenter.onButtonParkingSpaceReservationPickerPressed(this));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + SLASH + (month + ONE) + SLASH + year;
        presenter.onDateSetPressed(date, this);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = hourOfDay + TWO_POINTS + minute;
        presenter.onTimeSetPressed(time);
    }
}
