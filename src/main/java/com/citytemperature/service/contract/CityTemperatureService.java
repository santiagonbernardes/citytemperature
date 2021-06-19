package com.citytemperature.service.contract;

import com.citytemperature.domain.contract.CityTemperature;

public interface CityTemperatureService {
    CityTemperature findCityTemperature(final String cityName);
}
