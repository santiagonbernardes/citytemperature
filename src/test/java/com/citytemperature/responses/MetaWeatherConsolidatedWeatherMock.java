package com.citytemperature.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MetaWeatherConsolidatedWeatherMock {

  @JsonProperty("id")
  private final Long id;

  @JsonProperty("weather_state_name")
  private final String weatherStateName;

  @JsonProperty("weather_state_abbr")
  private final String weatherStateAbbr;

  @JsonProperty("wind_direction_compass")
  private final String windDirectionCompass;

  private final LocalDateTime created;

  @JsonProperty("applicable_date") // usar format?
  private final LocalDate applicableDate;

  @JsonProperty("min_temp")
  private final BigDecimal minTemp;

  @JsonProperty("max_temp")
  private final BigDecimal maxTemp;

  @JsonProperty("the_temp")
  private final BigDecimal theTemp;

  @JsonProperty("wind_speed")
  private final BigDecimal windSpeed;

  @JsonProperty("wind_direction")
  private final BigDecimal windDirection;

  @JsonProperty("air_pressure")
  private final BigDecimal airPressure;

  private final Integer humidity;
  private final BigDecimal visibility;
  private final Integer predictability;

  public MetaWeatherConsolidatedWeatherMock(
      final LocalDate applicableDate, final BigDecimal theTemp) {
    this.id = 6406936850333696L;
    this.weatherStateName = "Light Rain";
    this.weatherStateAbbr = "lr";
    this.windDirectionCompass = "ENE";
    this.created = LocalDateTime.now();
    this.applicableDate = applicableDate;
    this.minTemp = BigDecimal.valueOf(10.98);
    this.maxTemp = BigDecimal.valueOf(17.085);
    this.theTemp = theTemp;
    this.windSpeed = BigDecimal.valueOf(4.250440011954187);
    this.windDirection = BigDecimal.valueOf(77.11379606316441);
    this.airPressure = BigDecimal.valueOf(1015.0);
    this.humidity = 81;
    this.visibility = BigDecimal.valueOf(9.105884136642011);
    this.predictability = 75;
  }

  public Long getId() {
    return id;
  }

  public String getWeatherStateName() {
    return weatherStateName;
  }

  public String getWeatherStateAbbr() {
    return weatherStateAbbr;
  }

  public String getWindDirectionCompass() {
    return windDirectionCompass;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public LocalDate getApplicableDate() {
    return applicableDate;
  }

  public BigDecimal getMinTemp() {
    return minTemp;
  }

  public BigDecimal getMaxTemp() {
    return maxTemp;
  }

  public BigDecimal getTheTemp() {
    return theTemp;
  }

  public BigDecimal getWindSpeed() {
    return windSpeed;
  }

  public BigDecimal getWindDirection() {
    return windDirection;
  }

  public BigDecimal getAirPressure() {
    return airPressure;
  }

  public Integer getHumidity() {
    return humidity;
  }

  public BigDecimal getVisibility() {
    return visibility;
  }

  public Integer getPredictability() {
    return predictability;
  }
}
