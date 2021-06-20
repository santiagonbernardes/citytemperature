package com.citytemperature.helpers;

public class TemperatureConverter {
    public static Double convertFromCelsiusToFahrenheit(final Double temperatureInCelsius) {
        return (temperatureInCelsius * 1.8) + 32;
    }
}
