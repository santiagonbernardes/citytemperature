package com.citytemperature.controller;

import com.citytemperature.domain.contract.CityTemperature;
import com.citytemperature.domain.impl.MetaWeatherCityTemperatureImpl;
import com.citytemperature.exceptions.CityNotFoundException;
import com.citytemperature.exceptions.CityTemperatureNotFoundException;
import com.citytemperature.exceptions.MetaWeatherIntegrationException;
import com.citytemperature.service.contract.CityTemperatureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CityTemperatureControllerTest {

  @MockBean private CityTemperatureService cityTemperatureServiceMock;

  @Autowired private MockMvc mockMvc;

  @Test
  void shouldReturn200WhenServiceReturnsACityTemperatureAndUseItAsResponseBody() throws Exception {
    final CityTemperature expected =
        new MetaWeatherCityTemperatureImpl("Test", LocalDate.now(), BigDecimal.valueOf(30));
    when(this.cityTemperatureServiceMock.findCityTemperature(any())).thenReturn(expected);
    this.mockMvc
        .perform(getDefaultRequest())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.city").value(expected.getCityName()))
        .andExpect(jsonPath("$.date").value(expected.getDateThisTemperatureIsExpected().toString()))
        .andExpect(jsonPath("$.temperatureInCelsius").isNotEmpty())
        .andExpect(jsonPath("$.temperatureInFahrenheit").isNotEmpty());
  }

  @Test
  void
      shouldReturn400WhenUserInputAnInvalidCityNameAndResponseBodyShouldHaveAClearMessageAboutTheErrorInDetailsKey()
          throws Exception {
    final char[] stringChars = new char[100];
    Arrays.fill(stringChars, 'a');
    this.mockMvc
        .perform(get(String.format("/api/v1/cities/%s/temperature", new String(stringChars))))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.details").isNotEmpty());
  }

  @Test
  // A Parametrized test would be better but I don't know how to implement that right now.
  void
      shouldReturn404WhenServiceThrowsCityNotFoundExceptionAndResponseBodyShouldHaveTheMessageOfTheException()
          throws Exception {
    final String message = "Clear message about the error";
    when(this.cityTemperatureServiceMock.findCityTemperature(any()))
        .thenThrow(new CityNotFoundException(message));
    this.mockMvc
        .perform(getDefaultRequest())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.details").value(message));
  }

  @Test
  void
      shouldReturn404WhenServiceThrowsCityTemperatureNotFoundExceptionAndResponseBodyShouldHaveTheMessageOfTheException()
          throws Exception {
    final String message = "Another clear message about the error";
    when(this.cityTemperatureServiceMock.findCityTemperature(any()))
        .thenThrow(new CityTemperatureNotFoundException(message));
    this.mockMvc
        .perform(getDefaultRequest())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.details").value(message));
  }

  @Test
  void
      shouldReturn500WhenServiceThrowsAnUnexpectedExceptionAndResponseBodyShouldHaveADefaultMessageIgnoringExceptionMessage()
          throws Exception {
    final String expectedBody =
        "{'details': 'An error has occurred and your team is working on it. Try again later.'}";
    when(this.cityTemperatureServiceMock.findCityTemperature(any()))
        .thenThrow(new RuntimeException("Ignore me."));
    this.mockMvc
        .perform(getDefaultRequest())
        .andExpect(status().isInternalServerError())
        .andExpect(jsonPath("$.details").isNotEmpty())
        .andExpect(content().json(expectedBody));
  }

  @Test
  void
      shouldReturn502WhenServiceThrowsMetaWeatherIntegrationExceptionAndResponseBodyShouldHaveAClearMessageAboutTheError()
          throws Exception {
    when(this.cityTemperatureServiceMock.findCityTemperature(any()))
        .thenThrow(new MetaWeatherIntegrationException("Ignore me."));
    this.mockMvc
        .perform(getDefaultRequest())
        .andExpect(status().isBadGateway())
        .andExpect(jsonPath("$.details").isNotEmpty());
  }

  private MockHttpServletRequestBuilder getDefaultRequest() {
    return get("/api/v1/cities/test/temperature");
  }
}
