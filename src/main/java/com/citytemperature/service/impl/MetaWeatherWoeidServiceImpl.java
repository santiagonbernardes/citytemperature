package com.citytemperature.service.impl;

import com.citytemperature.domain.contract.Woeid;
import com.citytemperature.domain.impl.MetaWeatherWoeidImpl;
import com.citytemperature.exceptions.MetaWeatherIntegrationException;
import com.citytemperature.service.contract.WoeidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("MetaWeatherWoeidService")
public class MetaWeatherWoeidServiceImpl implements WoeidService {

    private final WebClient webClient;

    @Autowired
    public MetaWeatherWoeidServiceImpl(final WebClient metaWeatherWebClient) {
        this.webClient = metaWeatherWebClient;
    }

    @Override
    public List<Woeid> findAllWoeidByCitiesName(final String cityName) {
        final MetaWeatherWoeidImpl[] woeids = Optional.ofNullable(webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/")
                        .queryParam("query", cityName)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new MetaWeatherIntegrationException("MetaWeather returned error status code.")))
                .bodyToMono(MetaWeatherWoeidImpl[].class)
                .block())
                .orElse(new MetaWeatherWoeidImpl[0]);

        return Arrays.stream(woeids)
                .filter(woeid -> woeid.getType().equalsIgnoreCase("city"))
                .collect(Collectors.toList());
    }

}
