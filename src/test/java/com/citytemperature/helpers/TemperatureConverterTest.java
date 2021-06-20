package com.citytemperature.helpers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureConverterTest {

    @ParameterizedTest(name = "{0}ºC is equal to {1}ºF.")
    @MethodSource("getConversionParameters")
    void shouldConvertCelsiusToFahrenheitCorrectly(final Double input, final Double expected) {
        assertEquals(expected, TemperatureConverter.convertFromCelsiusToFahrenheit(input));
    }

    private static Stream<Arguments> getConversionParameters() {
        return Stream.of(
                Arguments.of(-40., -40.),
                Arguments.of(-30., -22.),
                Arguments.of(-20., -4.),
                Arguments.of(-10., 14.),
                Arguments.of(0., 32.),
                Arguments.of(10., 50.),
                Arguments.of(20., 68.),
                Arguments.of(30., 86.),
                Arguments.of(40., 104.),
                Arguments.of(50., 122.),
                Arguments.of(60., 140.),
                Arguments.of(70., 158.),
                Arguments.of(80., 176.),
                Arguments.of(90., 194.),
                Arguments.of(100., 212.),
                Arguments.of(59., 138.2),
                Arguments.of(-49., -56.2)
        );
    }
}