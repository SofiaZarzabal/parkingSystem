package com.example.parkingsystem.mvp.presenter;

import com.example.parkingsystem.mvp.contracts.ParkingContract;
import com.example.parkingsystem.mvp.model.ParkingModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ParkingPresenterTest {
    private ParkingContract.ParkingPresenter presenter;
    private ParkingContract.ParkingModel model;
    @Mock
    private ParkingContract.ParkingView view;
    private final int PARKING_SPACES = 5;

    @Before
    public void setup() {
        model = new ParkingModel();
        presenter = new ParkingPresenter(model, view);
    }

    @Test
    public void onButtonMainSelectParkingPressedTest() {
        presenter.onButtonMainSelectParkingPressed();
        verify(view).showParkingAlertDialog();
    }

    @Test
    public void onButtonDialogParkingConfirmPressedTest() {
        presenter.onButtonDialogParkingConfirmPressed(PARKING_SPACES);
        assertEquals(PARKING_SPACES, model.getParkingSpaces());
        verify(view).showParkingSpaces(model.getParkingSpaces());
    }

    @Test
    public void onButtonMainBookParkingLotPressedTest() {
        presenter.onButtonMainBookParkingLotPressed();
        verify(view).showParkingSpaceReservation();
    }
}
