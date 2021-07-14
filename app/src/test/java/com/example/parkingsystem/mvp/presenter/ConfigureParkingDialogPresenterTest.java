package com.example.parkingsystem.mvp.presenter;

import com.example.parkingsystem.listener.ConfigureParkingDialogListener;
import com.example.parkingsystem.mvp.contracts.ConfigureParkingDialogContract;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ConfigureParkingDialogPresenterTest {
    private ConfigureParkingDialogContract.ConfigureParkingDialogPresenter presenter;
    @Mock
    private ConfigureParkingDialogContract.ConfigureParkingDialogView view;
    @Mock
    private ConfigureParkingDialogListener listener;
    private final String EMPTY_PARKING_SPACES = "";
    private final String STRING_PARKING_SPACES = "5";
    private final int INT_PARKING_SPACES = 5;

    @Before
    public void setup() {
        presenter = new ConfigureParkingDialogPresenter(view);
    }

    @Test
    public void onButtonDialogParkingConfirmEmptyPressedTest() {
        when(view.getParkingSpaces()).thenReturn(EMPTY_PARKING_SPACES);
        presenter.onButtonDialogParkingConfirmPressed(listener);
        verify(view).showToastEmptyInput();
    }

    @Test
    public void onButtonDialogParkingConfirmNotEmptyPressedTest() {
        when(view.getParkingSpaces()).thenReturn(STRING_PARKING_SPACES);
        presenter.onButtonDialogParkingConfirmPressed(listener);
        verify(view).closeDialog();
        verify(view).showParkingSpaces(INT_PARKING_SPACES, listener);
    }

    @Test
    public void onButtonDialogParkingCancelPressedTest() {
        presenter.onButtonDialogParkingCancelPressed();
        verify(view).closeDialog();
    }
}
