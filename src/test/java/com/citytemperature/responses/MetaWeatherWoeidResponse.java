package com.citytemperature.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaWeatherWoeidResponse {

  private String title;

  @JsonProperty("location_type")
  private String locationType;

  private Integer woeid;

  @JsonProperty("latt_long")
  private String latLong;

  public MetaWeatherWoeidResponse(
      final String title, final String locationType, final Integer woeid, final String lat) {
    this.title = title;
    this.locationType = locationType;
    this.woeid = woeid;
    this.latLong = lat;
  }

  public MetaWeatherWoeidResponse() {}

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

  public Integer getWoeid() {
    return woeid;
  }

  public void setWoeid(final Integer woeid) {
    this.woeid = woeid;
  }

  public String getLatLong() {
    return latLong;
  }

  public void setLatLong(final String latLong) {
    this.latLong = latLong;
  }
}
