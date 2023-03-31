package com.example.prediction_crime.exceptions;

public enum ErrorCodes {

    CRIME_NOT_FOUND(404),
    CRIME_NOT_VALID(403),
    CRIME_ALREADY_IN_USE(402);



    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
