package com.citytemperature.domain.impl;

import com.citytemperature.domain.contract.Woeid;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaWeatherWoeidImpl implements Woeid {

    @JsonProperty("woeid")
    private String id;

    @JsonProperty("location_type")
    private String type;

    public MetaWeatherWoeidImpl(final String id, final String type) {
        this.id = id;
        this.type = type;
    }

    public MetaWeatherWoeidImpl() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
