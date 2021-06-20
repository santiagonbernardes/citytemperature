package com.citytemperature.controller.advice;

public class ErrorDetails {

    private final String details;

    public ErrorDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
