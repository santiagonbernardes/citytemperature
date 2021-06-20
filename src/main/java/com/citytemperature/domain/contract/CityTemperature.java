package com.citytemperature.domain.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CityTemperature {

    @JsonProperty("city")
    String getCityName();

    @JsonProperty("date")
    LocalDate getDateThisTemperatureIsExpected();

    @JsonProperty("temperatureInCelsius")
    BigDecimal getTemperatureInCelsius();

    @JsonProperty("temperatureInFahrenheit")
    BigDecimal getTemperatureInFahrenheit();
}
