package com.example.parkingsystem.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.example.parkingsystem.R;
import com.example.parkingsystem.fragment.ConfigureParkingDialog;
import com.example.parkingsystem.mvp.contracts.ParkingContract;
import com.example.parkingsystem.mvp.view.base.ActivityView;

public class ParkingView extends ActivityView implements ParkingContract.ParkingView {
    private final String FRAGMENT_TAG = "parkingDialog";

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
        DialogFragment dialog = new ConfigureParkingDialog();
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            dialog.show(fragmentManager, FRAGMENT_TAG);
        }
    }
}
