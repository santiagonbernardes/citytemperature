package com.citytemperature.backend.api.metaweather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaWeatherCityTemperatureResponse {
    @JsonProperty("consolidated_weather")
    private List<MetaWeatherConsolidatedWeather> consolidatedWeather;

    private ZonedDateTime time;

    @JsonProperty("sun_rise")
    private ZonedDateTime sunRise;

    @JsonProperty("sun_set")
    private ZonedDateTime sunSet;

    @JsonProperty("timezone_name")
    private String timezoneName;
    private MetaWeatherParent parent;
    private List<MetaWeatherSource> sources;
    private String title;

    @JsonProperty("location_type")
    private String locationType;

    @JsonProperty("woeid")
    private Long woeid;

    @JsonProperty("latt_long")
    private String latLong;

    @JsonProperty("timezone")
    private String timezone;

    public MetaWeatherCityTemperatureResponse() {
        // Jackson uses this.
    }

    public List<MetaWeatherConsolidatedWeather> getConsolidatedWeather() {
        return consolidatedWeather;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public ZonedDateTime getSunRise() {
        return sunRise;
    }

    public ZonedDateTime getSunSet() {
        return sunSet;
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public MetaWeatherParent getParent() {
        return parent;
    }

    public List<MetaWeatherSource> getSources() {
        return sources;
    }

    public String getTitle() {
        return title;
    }

    public String getLocationType() {
        return locationType;
    }

    public Long getWoeid() {
        return woeid;
    }

    public String getLatLong() {
        return latLong;
    }

    public String getTimezone() {
        return timezone;
    }
}
