package com.citytemperature.service.contract;

import com.citytemperature.domain.contract.Woeid;

import java.util.List;

public interface WoeidService {
    List<Woeid> findAllCitiesByName(final String cityName);
}
