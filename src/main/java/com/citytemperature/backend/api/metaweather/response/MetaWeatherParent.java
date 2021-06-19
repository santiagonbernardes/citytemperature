package com.citytemperature.backend.api.metaweather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaWeatherParent {
    private String title;

    @JsonProperty("location_type")
    private String locationType;
    private String woeid;

    @JsonProperty("latt_long")
    private String latLong;

    public MetaWeatherParent() {
        // Jackson uses this.
    }

    public String getTitle() {
        return title;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getWoeid() {
        return woeid;
    }

    public String getLatLong() {
        return latLong;
    }
}
