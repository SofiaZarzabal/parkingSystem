package com.example.parkingsystem.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.example.parkingsystem.R;
import com.example.parkingsystem.databinding.ActivityMainBinding;
import com.example.parkingsystem.mvp.contract.ParkingContract;
import com.example.parkingsystem.mvp.view.base.ActivityView;

public class ParkingView extends ActivityView implements ParkingContract.ParkingView {

    private final ActivityMainBinding binding;

    public ParkingView(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

    public void showParkingSpaces(int parkingLots) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.toast_main_activity_total_parking_lots, parkingLots), Toast.LENGTH_SHORT).show();
        }
    }
}
