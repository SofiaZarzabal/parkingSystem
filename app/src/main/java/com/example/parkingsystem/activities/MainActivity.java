package com.example.parkingsystem.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.parkingsystem.databinding.ActivityMainBinding;
import com.example.parkingsystem.listener.ConfigureParkingDialogListener;
import com.example.parkingsystem.mvp.contracts.ParkingContract;
import com.example.parkingsystem.mvp.model.ParkingModel;
import com.example.parkingsystem.mvp.presenter.ParkingPresenter;
import com.example.parkingsystem.mvp.view.ParkingView;

public class MainActivity extends AppCompatActivity implements ConfigureParkingDialogListener {

    private ActivityMainBinding binding;
    private ParkingContract.ParkingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ParkingPresenter(new ParkingModel(), new ParkingView(this));
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();
    }

    @Override
    public void onDialogPositiveClick(int parkingSpaces) {
        presenter.onButtonDialogParkingConfirmPressed(parkingSpaces);
    }

    public void setListener() {
        binding.buttonMainSelectParking.setOnClickListener(view -> presenter.onButtonMainSelectParkingPressed());
        binding.buttonMainBookParkingLot.setOnClickListener(view -> presenter.onButtonMainBookParkingLotPressed());
    }
}
