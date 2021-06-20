package com.citytemperature.service.impl;

import com.citytemperature.backend.api.metaweather.response.MetaWeatherCityTemperatureResponse;
import com.citytemperature.backend.api.metaweather.response.MetaWeatherConsolidatedWeather;
import com.citytemperature.domain.contract.CityTemperature;
import com.citytemperature.domain.contract.Woeid;
import com.citytemperature.domain.impl.MetaWeatherCityTemperatureImpl;
import com.citytemperature.exceptions.CityNotFoundException;
import com.citytemperature.exceptions.CityTemperatureNotFoundException;
import com.citytemperature.exceptions.MetaWeatherIntegrationException;
import com.citytemperature.service.contract.CityTemperatureService;
import com.citytemperature.service.contract.WoeidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

@Service("MetaWeatherCityTemperatureService")
public class MetaWeatherCityTemperatureServiceImpl implements CityTemperatureService {

  private final WebClient webClient;
  private final WoeidService woeidService;

  @Autowired
  public MetaWeatherCityTemperatureServiceImpl(
      @Qualifier("MetaWeatherWoeidService") final WoeidService woeidService,
      WebClient metaWeatherWebClient) {
    this.webClient = metaWeatherWebClient;
    this.woeidService = woeidService;
  }

  @Override
  public CityTemperature findCityTemperature(String cityName) {
    final List<Woeid> everyWoeidFound = this.woeidService.findAllWoeidByCitiesName(cityName);
    final String cityNotFoundMessage =
        String.format(
            "We found a city with the name %s, but we couldn't find its temperature.", cityName);

    if (everyWoeidFound.isEmpty()) {
      throw new CityNotFoundException(
          String.format("We couldn't find any city with the name %s.", cityName));
    }

    final MetaWeatherCityTemperatureResponse cityTemperature =
        this.webClient
            .get()
            .uri(uriBuilder -> uriBuilder.path("/{woeid}/").build(everyWoeidFound.get(0).getId()))
            .retrieve()
            .onStatus(
                HttpStatus::is4xxClientError,
                clientResponse ->
                    Mono.error(new CityTemperatureNotFoundException(cityNotFoundMessage)))
            .onStatus(
                HttpStatus::is5xxServerError,
                clientResponse ->
                    Mono.error(
                        new MetaWeatherIntegrationException(
                            "Meta Weather returned error status code.")))
            .bodyToMono(MetaWeatherCityTemperatureResponse.class)
            .block();

    if (cityTemperature == null
        || cityTemperature.getConsolidatedWeather() == null
        || cityTemperature.getConsolidatedWeather().isEmpty()) {
      throw new CityTemperatureNotFoundException(cityNotFoundMessage);
    }

    final MetaWeatherConsolidatedWeather mostRecentTemperature =
        cityTemperature.getConsolidatedWeather().stream()
            .min(Comparator.comparing(MetaWeatherConsolidatedWeather::getApplicableDate))
            .get();

    return new MetaWeatherCityTemperatureImpl(
        cityTemperature.getTitle(),
        mostRecentTemperature.getApplicableDate(),
        mostRecentTemperature.getTheTemp());
  }
}
