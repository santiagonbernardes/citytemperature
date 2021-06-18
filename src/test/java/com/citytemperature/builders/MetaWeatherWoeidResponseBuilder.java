package com.citytemperature.builders;

import com.citytemperature.responses.MetaWeatherWoeidResponse;

public class MetaWeatherWoeidResponseBuilder {

    private MetaWeatherWoeidResponse target;

    private MetaWeatherWoeidResponseBuilder() {
        this.target = new MetaWeatherWoeidResponse();
    }

    public static MetaWeatherWoeidResponseBuilder builder() {
        return new MetaWeatherWoeidResponseBuilder();
    }

    public MetaWeatherWoeidResponseBuilder withTitle(final String title) {
        this.target.setTitle(title);
        return this;
    }

    public MetaWeatherWoeidResponseBuilder withLocationType(final String locationType) {
        this.target.setLocationType(locationType);
        return this;
    }

    public MetaWeatherWoeidResponseBuilder withWoeid(final String woeid) {
        this.target.setWoeid(woeid);
        return this;
    }

    public MetaWeatherWoeidResponseBuilder withLattLong(final String lattLong) {
        this.target.setLattLong(lattLong);
        return this;
    }

    public MetaWeatherWoeidResponseBuilder withDefaultData() {
        this.target.setTitle("Default Title");
        this.target.setLocationType("Default LocationType");
        this.target.setWoeid("Default Woeid");
        this.target.setLattLong("Default LattLong");
        return this;
    }

    public MetaWeatherWoeidResponseBuilder withDefaultDataAndLocationTypeCity() {
        this.withDefaultData().withLocationType("City");
        return this;
    }

    public MetaWeatherWoeidResponse build() {
        return this.target;
    }
}
