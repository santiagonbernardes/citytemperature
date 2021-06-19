package com.citytemperature.exceptions;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(final String message) {
        super(message);
    }
}
