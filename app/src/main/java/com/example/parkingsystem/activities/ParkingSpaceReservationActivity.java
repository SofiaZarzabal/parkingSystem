package com.example.parkingsystem.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import com.example.parkingsystem.database.ParkingSpaceReservationDB;
import com.example.parkingsystem.databinding.ActivityReservationSpaceParkingBinding;
import com.example.parkingsystem.mvp.contracts.ParkingSpaceReservationContract;
import com.example.parkingsystem.mvp.model.ParkingSpaceReservationModel;
import com.example.parkingsystem.mvp.presenter.ParkingSpaceReservationPresenter;
import com.example.parkingsystem.mvp.view.ParkingSpaceReservationView;

public class ParkingSpaceReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private ActivityReservationSpaceParkingBinding binding;
    private ParkingSpaceReservationContract.ParkingSpaceReservationPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReservationSpaceParkingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ParkingSpaceReservationPresenter(new ParkingSpaceReservationModel(ParkingSpaceReservationDB.getInstance()), new ParkingSpaceReservationView(this, binding));
        setListener();
    }

    public void setListener() {
        binding.buttonParkingSpaceReservationPickerBegin.setOnClickListener(view -> presenter.onButtonParkingSpaceReservationPickerPressed(this));
        binding.buttonParkingSpaceReservationPickerEnd.setOnClickListener(view -> presenter.onButtonParkingSpaceReservationPickerPressed(this));
        binding.buttonParkingSpaceReservationSave.setOnClickListener(view -> presenter.onButtonParkingSpaceReservationSavePressed());
        binding.buttonParkingSpaceReservationDelete.setOnClickListener(view -> presenter.onButtonParkingSpaceReservationDeletePressed());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        presenter.onDateSetPressed(year, month, dayOfMonth, this);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        presenter.onTimeSetPressed(hourOfDay, minute);
    }
}
