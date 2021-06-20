package com.citytemperature.domain.impl;

import com.citytemperature.domain.contract.CityTemperature;
import com.citytemperature.helpers.TemperatureConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class MetaWeatherCityTemperatureImpl implements CityTemperature {

    private final String cityName;
    private final LocalDate date;
    private final BigDecimal temperatureInCelsius;

    public MetaWeatherCityTemperatureImpl(final String cityName, final LocalDate date, final BigDecimal temperatureInCelsius) {
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
    public BigDecimal getTemperatureInCelsius() {
        return withTwoDecimalPlaces(this.temperatureInCelsius);
    }

    @Override
    public BigDecimal getTemperatureInFahrenheit() {
        return withTwoDecimalPlaces(TemperatureConverter.convertFromCelsiusToFahrenheit(this.temperatureInCelsius));
    }

    private static BigDecimal withTwoDecimalPlaces(final BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }
}
