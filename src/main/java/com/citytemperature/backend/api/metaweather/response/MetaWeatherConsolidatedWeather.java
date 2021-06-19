package com.citytemperature.backend.api.metaweather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private Double minTemp;

    @JsonProperty("max_temp")
    private Double maxTemp;

    @JsonProperty("the_temp")
    private Double theTemp;

    @JsonProperty("wind_speed")
    private Double windSpeed;

    @JsonProperty("wind_direction")
    private Double windDirection;

    @JsonProperty("air_pressure")
    private Double airPressure;
    private Integer humidity;
    private Double visibility;
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

    public Double getMinTemp() {
        return minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public Double getTheTemp() {
        return theTemp;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public Double getAirPressure() {
        return airPressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Double getVisibility() {
        return visibility;
    }

    public Integer getPredictability() {
        return predictability;
    }
}
