package com.citytemperature.helpers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TemperatureConverterTest {

  @ParameterizedTest(name = "{0}ºC is equal to {1}ºF.")
  @MethodSource("getConversionParameters")
  void shouldConvertCelsiusToFahrenheitCorrectly(
      final BigDecimal input, final BigDecimal expected) {
    assertEquals(0, expected.compareTo(TemperatureConverter.convertFromCelsiusToFahrenheit(input)));
  }

  private static Stream<Arguments> getConversionParameters() {
    return Stream.of(
        Arguments.of(BigDecimal.valueOf(-40.), BigDecimal.valueOf(-40.)),
        Arguments.of(BigDecimal.valueOf(-30.), BigDecimal.valueOf(-22.)),
        Arguments.of(BigDecimal.valueOf(-20.), BigDecimal.valueOf(-4.)),
        Arguments.of(BigDecimal.valueOf(-10.), BigDecimal.valueOf(14.)),
        Arguments.of(BigDecimal.valueOf(0.), BigDecimal.valueOf(32.)),
        Arguments.of(BigDecimal.valueOf(10.), BigDecimal.valueOf(50.)),
        Arguments.of(BigDecimal.valueOf(20.), BigDecimal.valueOf(68.)),
        Arguments.of(BigDecimal.valueOf(30.), BigDecimal.valueOf(86.)),
        Arguments.of(BigDecimal.valueOf(40.), BigDecimal.valueOf(104.)),
        Arguments.of(BigDecimal.valueOf(50.), BigDecimal.valueOf(122.)),
        Arguments.of(BigDecimal.valueOf(60.), BigDecimal.valueOf(140.)),
        Arguments.of(BigDecimal.valueOf(70.), BigDecimal.valueOf(158.)),
        Arguments.of(BigDecimal.valueOf(80.), BigDecimal.valueOf(176.)),
        Arguments.of(BigDecimal.valueOf(90.), BigDecimal.valueOf(194.)),
        Arguments.of(BigDecimal.valueOf(100.), BigDecimal.valueOf(212.)),
        Arguments.of(BigDecimal.valueOf(59.), BigDecimal.valueOf(138.2)),
        Arguments.of(BigDecimal.valueOf(-49.), BigDecimal.valueOf(-56.2)));
  }
}
