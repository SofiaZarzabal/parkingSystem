package com.example.parkingsystem.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.parkingsystem.databinding.DialogParkingConfigureBinding;
import com.example.parkingsystem.listener.ConfigureParkingDialogListener;
import com.example.parkingsystem.mvp.contracts.ConfigureParkingDialogContract;
import com.example.parkingsystem.mvp.presenter.ConfigureParkingDialogPresenter;
import com.example.parkingsystem.mvp.view.ConfigureParkingDialogView;

public class ConfigureParkingDialog extends DialogFragment {
    private DialogParkingConfigureBinding binding;
    private ConfigureParkingDialogListener listener;
    private ConfigureParkingDialogContract.ConfigureParkingDialogPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DialogParkingConfigureBinding.inflate(getLayoutInflater());
        setListeners();
        listener = ((ConfigureParkingDialogListener) getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        presenter = new ConfigureParkingDialogPresenter(new ConfigureParkingDialogView(this, binding));
    }

    public void setListeners() {
        binding.buttonDialogFragmentConfirm.setOnClickListener(view -> presenter.onButtonDialogParkingConfirmPressed(listener));
        binding.buttonDialogFragmentCancel.setOnClickListener(view -> presenter.onButtonDialogParkingCancelPressed());
    }
}
