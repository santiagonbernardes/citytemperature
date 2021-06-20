package com.citytemperature.backend.api.metaweather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaWeatherConsolidatedWeather {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("weather_state_name")
  private String weatherStateName;

  @JsonProperty("weather_state_abbr")
  private String weatherStateAbbr;

  @JsonProperty("wind_direction_compass")
  private String windDirectionCompass;

  private LocalDateTime created;

  @JsonProperty("applicable_date")
  private LocalDate applicableDate;

  @JsonProperty("min_temp")
  private BigDecimal minTemp;

  @JsonProperty("max_temp")
  private BigDecimal maxTemp;

  @JsonProperty("the_temp")
  private BigDecimal theTemp;

  @JsonProperty("wind_speed")
  private BigDecimal windSpeed;

  @JsonProperty("wind_direction")
  private BigDecimal windDirection;

  @JsonProperty("air_pressure")
  private BigDecimal airPressure;

  private Integer humidity;
  private BigDecimal visibility;
  private Integer predictability;

  public MetaWeatherConsolidatedWeather() {
    // Jackson uses this.
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
