package com.citytemperature.helpers;

import java.math.BigDecimal;

public class TemperatureConverter {

  private TemperatureConverter() {
    // Hiding public constructor
  }

  public static BigDecimal convertFromCelsiusToFahrenheit(final BigDecimal temperatureInCelsius) {
    // (temperatureInCelsius * 1.8) + 32
    return temperatureInCelsius.multiply(BigDecimal.valueOf(1.8)).add(BigDecimal.valueOf(32));
  }
}
