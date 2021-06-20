package com.citytemperature.backend.api.metaweather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaWeatherSource {

  private String title;
  private String slug;
  private String url;

  @JsonProperty("crawl_rate")
  private Integer crawlRate;

  public MetaWeatherSource() {
    // Jackson uses this.
  }

  public String getTitle() {
    return title;
  }

  public String getSlug() {
    return slug;
  }

  public String getUrl() {
    return url;
  }

  public Integer getCrawlRate() {
    return crawlRate;
  }
}
