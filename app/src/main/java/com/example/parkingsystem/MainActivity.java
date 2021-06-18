package com.example.parkingsystem;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.parkingsystem.databinding.ActivityMainBinding;
import com.example.parkingsystem.mvp.contract.ParkingContract;
import com.example.parkingsystem.mvp.model.ParkingModel;
import com.example.parkingsystem.mvp.presenter.ParkingPresenter;
import com.example.parkingsystem.mvp.view.ParkingView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ParkingContract.ParkingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ParkingPresenter(new ParkingModel(), new ParkingView(this, this.binding));
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();
    }

    public void setListener() {
        binding.buttonMainSelectParking.setOnClickListener(view -> presenter.onButtonMainSelectParkingPressed());
    }
}
