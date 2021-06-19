package com.citytemperature.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MetaWeatherParentMock {

    DEFAULT("England", "Region / State / Province", "24554868", "52.883560,-1.974060");

    private final String title;

    @JsonProperty("location_type")
    private final String locationType;
    private final String woeid;
    @JsonProperty("latt_long")
    private final String latLong;

    MetaWeatherParentMock(final String title, final String locationType, final String woeid, final String latLong) {
        this.title = title;
        this.locationType = locationType;
        this.woeid = woeid;
        this.latLong = latLong;
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
