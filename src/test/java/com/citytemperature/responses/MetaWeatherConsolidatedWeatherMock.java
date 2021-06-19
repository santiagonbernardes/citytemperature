package com.citytemperature.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private final Double minTemp;

    @JsonProperty("max_temp")
    private final Double maxTemp;

    @JsonProperty("the_temp")
    private final Double theTemp;

    @JsonProperty("wind_speed")
    private final Double windSpeed;

    @JsonProperty("wind_direction")
    private final Double windDirection;

    @JsonProperty("air_pressure")
    private final Double airPressure;
    private final Integer humidity;
    private final Double visibility;
    private final Integer predictability;

    public MetaWeatherConsolidatedWeatherMock(final LocalDate applicableDate, final Double theTemp) {
        this.id = 6406936850333696L;
        this.weatherStateName = "Light Rain";
        this.weatherStateAbbr = "lr";
        this.windDirectionCompass = "ENE";
        this.created = LocalDateTime.now();
        this.applicableDate = applicableDate;
        this.minTemp = 10.98;
        this.maxTemp = 17.085;
        this.theTemp = theTemp;
        this.windSpeed = 4.250440011954187;
        this.windDirection = 77.11379606316441;
        this.airPressure = 1015.0;
        this.humidity = 81;
        this.visibility = 9.105884136642011;
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
