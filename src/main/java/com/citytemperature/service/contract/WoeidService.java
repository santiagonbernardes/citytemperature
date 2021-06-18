package com.citytemperature.service.contract;

import com.citytemperature.domain.contract.Woeid;

import java.util.List;

public interface WoeidService {
    List<Woeid> findAllWoeidByCitiesName(final String cityName);
}
