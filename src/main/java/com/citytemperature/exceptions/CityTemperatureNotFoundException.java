package com.citytemperature.exceptions;

public class CityTemperatureNotFoundException extends RuntimeException {

    public CityTemperatureNotFoundException(String message) {
        super(message);
    }
}
