package com.citytemperature.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MetaWeatherSourceMock {
  BBC("BBC", "bbc", "http://www.bbc.co.uk/weather/", 360),
  FORECAST_IO("Forecast.io", "forecast-io", "http://forecast.io/", 480),
  HAMWEATHER("HAMweather", "hamweather", "http://www.hamweather.com/", 360),
  MET_OFFICE("Met Office", "met-office", "http://www.metoffice.gov.uk/", 180),
  OPEN_WEATHER_MAP("OpenWeatherMap", "openweathermap", "http://openweathermap.org/", 360),
  WEATHER_UNDERGROUND(
      "Weather Underground",
      "wunderground",
      "https://www.wunderground.com/?apiref=fc30dc3cd224e19b",
      720),
  WORLD_WEATHER_ONLINE(
      "World Weather Online", "world-weather-online", "http://www.worldweatheronline.com/", 360);

  private final String title;
  private final String slug;
  private final String url;

  @JsonProperty("crawl_rate")
  private final Integer crawlRate;

  MetaWeatherSourceMock(
      final String title, final String slug, final String url, final Integer crawlRate) {
    this.title = title;
    this.slug = slug;
    this.url = url;
    this.crawlRate = crawlRate;
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
