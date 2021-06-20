package com.citytemperature.domain.impl;

import com.citytemperature.domain.contract.CityTemperature;
import com.citytemperature.helpers.TemperatureConverter;

import java.time.LocalDate;

public class MetaWeatherCityTemperatureImpl implements CityTemperature {

    private final String cityName;
    private final LocalDate date;
    private final Double temperatureInCelsius;

    public MetaWeatherCityTemperatureImpl(final String cityName, final LocalDate date, final Double temperatureInCelsius) {
        this.cityName = cityName;
        this.date = date;
        this.temperatureInCelsius = temperatureInCelsius;
    }

    @Override
    public String getCityName() {
        return this.cityName;
    }

    @Override
    public LocalDate getDateThisTemperatureIsExpected() {
        return this.date;
    }

    @Override
    public Double getTemperatureInCelsius() {
        return this.temperatureInCelsius;
    }

    @Override
    public Double getTemperatureInFahrenheit() {
        return TemperatureConverter.convertFromCelsiusToFahrenheit(this.temperatureInCelsius);
    }
}
