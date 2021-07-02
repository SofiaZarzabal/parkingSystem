package com.example.parkingsystem.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.example.parkingsystem.R;
import com.example.parkingsystem.activities.ParkingSpaceReservationActivity;
import com.example.parkingsystem.fragment.ConfigureParkingDialog;
import com.example.parkingsystem.mvp.contracts.ParkingContract;
import com.example.parkingsystem.mvp.view.base.ActivityView;
import com.example.parkingsystem.util.Constants;

public class ParkingView extends ActivityView implements ParkingContract.ParkingView {

    public ParkingView(Activity activity) {
        super(activity);
    }

    @Override
    public void showParkingSpaces(int parkingSpaces) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.toast_main_activity_total_parking_lots, parkingSpaces), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showParkingAlertDialog() {
        Context context = getContext();
        FragmentManager fragmentManager = getFragmentManager();
        if(context != null){
            if (fragmentManager != null) {
                DialogFragment dialog = new ConfigureParkingDialog();
                dialog.show(fragmentManager, Constants.TAG_PARKING_VIEW);
            }
        }
    }

    @Override
    public void showParkingSpaceReservation() {
        Activity activity = getActivity();
        if(activity != null){
            Intent intent = new Intent(activity, ParkingSpaceReservationActivity.class);
            activity.startActivity(intent);
        }
    }
}
