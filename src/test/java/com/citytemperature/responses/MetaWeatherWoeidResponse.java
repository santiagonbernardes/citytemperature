package com.citytemperature.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaWeatherWoeidResponse {

    private String title;

    @JsonProperty("location_type")
    private String locationType;
    private String woeid;

    @JsonProperty("latt_long")
    private String lattLong;

    public MetaWeatherWoeidResponse(final String title, final String locationType, final String woeid, final String lat) {
        this.title = title;
        this.locationType = locationType;
        this.woeid = woeid;
        this.lattLong = lat;
    }

    public MetaWeatherWoeidResponse() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(final String locationType) {
        this.locationType = locationType;
    }

    public String getWoeid() {
        return woeid;
    }

    public void setWoeid(final String woeid) {
        this.woeid = woeid;
    }

    public String getLattLong() {
        return lattLong;
    }

    public void setLattLong(final String lattLong) {
        this.lattLong = lattLong;
    }
}
