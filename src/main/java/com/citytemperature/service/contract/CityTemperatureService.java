package com.citytemperature.service.contract;

import com.citytemperature.domain.contract.Woeid;
import com.citytemperature.dto.CityTemperatureResponseDto;

import java.util.List;

public interface CityTemperatureService {
    List<CityTemperatureResponseDto> findCitiesTemperature(final List<Woeid> citiesWoeid);
}
