package com.example.parkingsystem.mvp.presenter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import com.example.parkingsystem.database.ParkingSpaceReservationDB;
import com.example.parkingsystem.entity.Reservation;
import com.example.parkingsystem.mvp.contracts.ParkingSpaceReservationContract;
import com.example.parkingsystem.mvp.model.ParkingSpaceReservationModel;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ParkingSpaceReservationPresenterTest {
    private final int YEAR_START = 2021;
    private final int MONTH_START = 8;
    private final int MONTH_START_PLUS_ONE = 9;
    private final int DAY_START = 5;
    private final int DAY_START_OVERLAP = 7;
    private final int DAY_START_SUCCESS = 9;
    private final String DATE_START_STRING = "5/9/2021";
    private final int YEAR_END = 2021;
    private final int MONTH_END = 8;
    private final int DAY_END = 9;
    private final int HOUR_START = 6;
    private final int MINUTE_START = 30;
    private final String TIME_START_STRING = "6:30";
    private final int HOUR_END = 7;
    private final int MINUTE_END = 45;
    private final String PARKING_SPACE_STRING = "2";
    private final String SECURITY_CODE_STRING = "235";
    private final int PARKING_SPACE_INT = 2;
    private final int SECURITY_CODE_INT = 873;
    private final String EMPTY_STRING = "";
    private final int ZERO_INT = 0;
    private final static String FORMAT_DATE = "dd/MM/yy";
    private final static String FORMAT_TIME = "HH:mm";
    ParkingSpaceReservationContract.ParkingSpaceReservationPresenter presenter;
    ParkingSpaceReservationContract.ParkingSpaceReservationModel model;
    @Mock
    ParkingSpaceReservationContract.ParkingSpaceReservationModel mockModel;
    @Mock
    ParkingSpaceReservationContract.ParkingSpaceReservationView view;
    @Mock
    DatePickerDialog.OnDateSetListener datePickerListener;
    @Mock
    TimePickerDialog.OnTimeSetListener timePickerListener;
    @Mock
    Reservation reservation;

    @Before
    public void setup() {
        /*MockitoAnnotations.initMocks(this);*/
        model = new ParkingSpaceReservationModel(ParkingSpaceReservationDB.getInstance());
        presenter = new ParkingSpaceReservationPresenter(model, view);
    }

    private Calendar createTimeCalendar(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar;
    }

    private Calendar createDateCalendar(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar;
    }

    private Reservation createReservation() {
        Reservation reservation = new Reservation();
        reservation.setDateStart(createDateCalendar(YEAR_START, MONTH_START, DAY_START_OVERLAP));
        reservation.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START));
        reservation.setDateEnd(createDateCalendar(YEAR_END, MONTH_END, DAY_END));
        reservation.setTimeEnd(createTimeCalendar(HOUR_END, MINUTE_END));
        reservation.setSecurityCode(SECURITY_CODE_INT);
        reservation.setParkingSpace(PARKING_SPACE_INT);
        return reservation;
    }

    @Test
    public void onFirstButtonParkingSpaceReservationPickerPressed() {
        when(view.getButtonPickerStart()).thenReturn(true);

        presenter.onButtonParkingSpaceReservationPickerPressed(datePickerListener);

        verify(view).showDatePickerDialog(datePickerListener);
    }

    @Test
    public void onSecondButtonParkingSpaceReservationPickerPressed() {
        when(view.getButtonPickerStart()).thenReturn(false);

        presenter.onButtonParkingSpaceReservationPickerPressed(datePickerListener);

        verify(view).showDatePickerDialog(datePickerListener);
    }

    @Test
    public void onMissingDateStartTest() {
        when(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING);
        when(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING);

        presenter.onButtonParkingSpaceReservationSavePressed();

        verify(view).showReleasedPastReservations(ZERO_INT);
        verify(view).showMissingDateStart();
    }

    @Test
    public void onMissingTimeStartTest() {
        when(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING);
        when(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING);
        model.setDateStart(createDateCalendar(YEAR_START, MONTH_START, DAY_START));

        presenter.onButtonParkingSpaceReservationSavePressed();

        verify(view).showReleasedPastReservations(ZERO_INT);
        verify(view).showMissingTimeStart();
    }

    @Test
    public void onMissingDateEndTest() {
        when(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING);
        when(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING);
        model.setDateStart(createDateCalendar(YEAR_START, MONTH_START, DAY_START));
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START));

        presenter.onButtonParkingSpaceReservationSavePressed();

        verify(view).showReleasedPastReservations(ZERO_INT);
        verify(view).showMissingDateEnd();
    }

    @Test
    public void onMissingTimeEndTest() {
        when(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING);
        when(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING);
        model.setDateStart(createDateCalendar(YEAR_START, MONTH_START, DAY_START));
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START));
        model.setDateEnd(createDateCalendar(YEAR_END, MONTH_END, DAY_END));

        presenter.onButtonParkingSpaceReservationSavePressed();

        verify(view).showReleasedPastReservations(ZERO_INT);
        verify(view).showMissingTimeEnd();
    }

    @Test
    public void onMissingParkingSpace() {
        when(view.getParkingSpace()).thenReturn(EMPTY_STRING);
        when(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING);
        model.setDateStart(createDateCalendar(YEAR_START, MONTH_START, DAY_START));
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START));
        model.setDateEnd(createDateCalendar(YEAR_END, MONTH_END, DAY_END));
        model.setTimeEnd(createTimeCalendar(HOUR_END, MINUTE_END));

        presenter.onButtonParkingSpaceReservationSavePressed();

        verify(view).showReleasedPastReservations(ZERO_INT);
        verify(view).showMissingParkingSpace();
    }

    @Test
    public void onMissingSecurityCode() {
        when(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING);
        when(view.getSecurityCode()).thenReturn(EMPTY_STRING);
        model.setDateStart(createDateCalendar(YEAR_START, MONTH_START, DAY_START));
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START));
        model.setDateEnd(createDateCalendar(YEAR_END, MONTH_END, DAY_END));
        model.setTimeEnd(createTimeCalendar(HOUR_END, MINUTE_END));

        presenter.onButtonParkingSpaceReservationSavePressed();

        verify(view).showReleasedPastReservations(ZERO_INT);
        verify(view).showMissingSecurityCode();
    }

    @Test
    public void onReservationOverlap() {
        model.makeReservation(createReservation());
        when(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING);
        when(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING);
        model.setDateStart(createDateCalendar(YEAR_START, MONTH_START, DAY_START));
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START));
        model.setDateEnd(createDateCalendar(YEAR_END, MONTH_END, DAY_END));
        model.setTimeEnd(createTimeCalendar(HOUR_END, MINUTE_END));

        presenter.onButtonParkingSpaceReservationSavePressed();

        verify(view).showReleasedPastReservations(ZERO_INT);
        verify(view).showReservationOverlapping();
    }

    @Test
    public void onSuccessCheck() {
        when(view.getParkingSpace()).thenReturn(PARKING_SPACE_STRING);
        when(view.getSecurityCode()).thenReturn(SECURITY_CODE_STRING);
        model.setDateStart(createDateCalendar(YEAR_START, MONTH_START, DAY_START_SUCCESS));
        model.setTimeStart(createTimeCalendar(HOUR_START, MINUTE_START));
        model.setDateEnd(createDateCalendar(YEAR_END, MONTH_END, DAY_END));
        model.setTimeEnd(createTimeCalendar(HOUR_END, MINUTE_END));

        presenter.onButtonParkingSpaceReservationSavePressed();

        verify(view).showReleasedPastReservations(ZERO_INT);
        verify(view).showSaveDone();
    }

    @Test
    public void onDateSetPressedStartButtonTest() {
        ParkingSpaceReservationContract.ParkingSpaceReservationPresenter presenterWithMockModel = new ParkingSpaceReservationPresenter(
                mockModel,
                view
        );
        Calendar dateCalendar = createDateCalendar(YEAR_START, MONTH_START_PLUS_ONE, DAY_START);
        when(mockModel.convertToCalendar(DATE_START_STRING, FORMAT_DATE)).thenReturn(dateCalendar);
        when(mockModel.getDateStartButtonPressed()).thenReturn(true);

        presenterWithMockModel.onDateSetPressed(YEAR_START, MONTH_START, DAY_START, timePickerListener);

        verify(mockModel).setDateStart(dateCalendar);
        verify(view).showTimePickerDialog(timePickerListener);
    }

    @Test
    public void onDateSetPressedEndButtonTest() {
        ParkingSpaceReservationContract.ParkingSpaceReservationPresenter presenterWithMockModel = new ParkingSpaceReservationPresenter(
                mockModel,
                view
        );
        Calendar dateCalendar = createDateCalendar(YEAR_START, MONTH_START_PLUS_ONE, DAY_START);
        when(mockModel.convertToCalendar(DATE_START_STRING, FORMAT_DATE)).thenReturn(dateCalendar);
        when(mockModel.getDateStartButtonPressed()).thenReturn(false);

        presenterWithMockModel.onDateSetPressed(YEAR_START, MONTH_START, DAY_START, timePickerListener);

        verify(mockModel).setDateEnd(dateCalendar);
        verify(view).showTimePickerDialog(timePickerListener);
    }

    @Test
    public void onTimeSetPressedStartButtonTest() {
        ParkingSpaceReservationContract.ParkingSpaceReservationPresenter presenterWithMockModel = new ParkingSpaceReservationPresenter(
                mockModel,
                view
        );
        Calendar dateCalendar = createDateCalendar(YEAR_START, MONTH_START_PLUS_ONE, DAY_START);
        Calendar timeCalendar = createTimeCalendar(HOUR_START, MINUTE_START);
        when(mockModel.convertToCalendar(TIME_START_STRING, FORMAT_TIME)).thenReturn(timeCalendar);
        when(mockModel.getDateStartButtonPressed()).thenReturn(true);
        when(mockModel.getDateStart()).thenReturn(dateCalendar);
        when(mockModel.getTimeStart()).thenReturn(timeCalendar);
        when(reservation.getFormattedDate(mockModel.getDateStart())).thenReturn(DATE_START_STRING);
        when(reservation.getFormattedTime(mockModel.getTimeStart())).thenReturn(TIME_START_STRING);
        when(mockModel.getReservation()).thenReturn(reservation);

        presenterWithMockModel.onTimeSetPressed(HOUR_START, MINUTE_START);

        verify(mockModel).setTimeStart(timeCalendar);
        verify(view).enableButtonEnd();
        verify(view).showDateAndTimeStart(DATE_START_STRING, TIME_START_STRING);
    }

    @Test
    public void onTimeSetPressedEndButtonTest() {
        ParkingSpaceReservationContract.ParkingSpaceReservationPresenter presenterWithMockModel = new ParkingSpaceReservationPresenter(
                mockModel,
                view
        );
        Calendar dateCalendar = createDateCalendar(YEAR_START, MONTH_START_PLUS_ONE, DAY_START);
        Calendar timeCalendar = createTimeCalendar(HOUR_START, MINUTE_START);
        when(mockModel.convertToCalendar(TIME_START_STRING, FORMAT_TIME)).thenReturn(timeCalendar);
        when(mockModel.getDateStartButtonPressed()).thenReturn(false);
        when(mockModel.getDateEnd()).thenReturn(dateCalendar);
        when(mockModel.getTimeEnd()).thenReturn(timeCalendar);
        when(reservation.getFormattedDate(mockModel.getDateEnd())).thenReturn(DATE_START_STRING);
        when(reservation.getFormattedTime(mockModel.getTimeEnd())).thenReturn(TIME_START_STRING);
        when(mockModel.getReservation()).thenReturn(reservation);

        presenterWithMockModel.onTimeSetPressed(HOUR_START, MINUTE_START);

        verify(mockModel).setTimeEnd(timeCalendar);
        verify(view).showDateAndTimeEnd(DATE_START_STRING, TIME_START_STRING);
    }

    @Test
    public void onButtonParkingSpaceReservationDeletePressedTest() {
        presenter.onButtonParkingSpaceReservationDeletePressed();

        verify(view).showReleasedPastReservations(ZERO_INT);
    }
}
