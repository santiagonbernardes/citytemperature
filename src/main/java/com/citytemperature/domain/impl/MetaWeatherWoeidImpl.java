package com.citytemperature.domain.impl;

import com.citytemperature.domain.contract.Woeid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaWeatherWoeidImpl implements Woeid {

  private final Integer id;
  private final String type;

  public MetaWeatherWoeidImpl(
      @JsonProperty("woeid") final Integer id, @JsonProperty("location_type") final String type) {
    this.id = id;
    this.type = type;
  }

  @Override
  public Integer getId() {
    return this.id;
  }

  @Override
  public String getType() {
    return this.type;
  }
}
