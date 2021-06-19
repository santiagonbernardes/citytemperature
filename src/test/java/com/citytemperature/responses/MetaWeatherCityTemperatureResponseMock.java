package com.citytemperature.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class MetaWeatherCityTemperatureResponseMock {

    @JsonProperty("consolidated_weather")
    private final List<MetaWeatherConsolidatedWeatherMock> consolidatedWeather;

    private final ZonedDateTime time;

    @JsonProperty("sun_rise")
    private final ZonedDateTime sunRise;

    @JsonProperty("sun_set")
    private final ZonedDateTime sunSet;

    @JsonProperty("timezone_name")
    private final String timezoneName;
    private final MetaWeatherParentMock parent;
    private final List<MetaWeatherSourceMock> sources;
    private final String title;

    @JsonProperty("location_type")
    private final String locationType;

    @JsonProperty("woeid")
    private final Long woeid;

    @JsonProperty("latt_long")
    private final String latLong;

    @JsonProperty("timezone")
    private final String timezone;

    public MetaWeatherCityTemperatureResponseMock(List<MetaWeatherConsolidatedWeatherMock> consolidatedWeather, String title) {
        final ZonedDateTime now = ZonedDateTime.now();
        this.consolidatedWeather = consolidatedWeather;
        this.time = now;
        this.sunRise = now;
        this.sunSet = now;
        this.timezoneName = "LMT";
        this.parent = MetaWeatherParentMock.DEFAULT;
        this.sources = Arrays.asList(MetaWeatherSourceMock.values());
        this.title = title;
        this.locationType = "City";
        this.woeid = 44418L;
        this.latLong = "51.506321,-0.12714";
        this.timezone = "Europe/London";
    }

    public List<MetaWeatherConsolidatedWeatherMock> getConsolidatedWeather() {
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

    public MetaWeatherParentMock getParent() {
        return parent;
    }

    public List<MetaWeatherSourceMock> getSources() {
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
