package com.citytemperature.service.impl;

import com.citytemperature.domain.contract.Woeid;
import com.citytemperature.domain.impl.MetaWeatherWoeidImpl;
import com.citytemperature.service.contract.WoeidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
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
        return Arrays.stream(webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/")
                        .queryParam("query", cityName)
                        .build())
                .retrieve()
                .bodyToMono(MetaWeatherWoeidImpl[].class)
                .block())
                .filter(woeid -> woeid.getType().equalsIgnoreCase("city"))
                .collect(Collectors.toList());
    }

}
