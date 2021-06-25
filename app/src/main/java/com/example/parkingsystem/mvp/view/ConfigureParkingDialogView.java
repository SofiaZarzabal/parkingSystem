package com.example.parkingsystem.mvp.view;

import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import com.example.parkingsystem.R;
import com.example.parkingsystem.databinding.DialogParkingConfigureBinding;
import com.example.parkingsystem.listener.ConfigureParkingDialogListener;
import com.example.parkingsystem.mvp.contracts.ConfigureParkingDialogContract;
import com.example.parkingsystem.mvp.view.base.FragmentView;

public class ConfigureParkingDialogView extends FragmentView implements ConfigureParkingDialogContract.ConfigureParkingDialogView {
    private DialogParkingConfigureBinding binding;

    public ConfigureParkingDialogView(DialogFragment fragment, DialogParkingConfigureBinding binding) {
        super(fragment);
        this.binding = binding;
    }

    @Override
    public String getParkingSpaces() {
        return binding.inputFragmentDialog.getText().toString();
    }

    @Override
    public void closeDialog() {
        DialogFragment dialog = (DialogFragment) getFragment();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void showToastEmptyInput() {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.toast_configure_parking_dialog_empty_input), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showParkingSpaces(int intParkingSpaces, ConfigureParkingDialogListener listener) {
        listener.onDialogPositiveClick(intParkingSpaces);
    }
}
