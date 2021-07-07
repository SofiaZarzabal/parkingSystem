package com.example.parkingsystem.utils;

public enum ReservationVerificationResult {
    MISSING_DATE_START,
    MISSING_TIME_START,
    MISSING_DATE_END,
    MISSING_TIME_END,
    MISSING_PARKING_SPACE,
    MISSING_SECURITY_CODE,
    RESERVATION_OVERLAPPING,
    SUCCESS
}
