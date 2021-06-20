package com.citytemperature.controller;

import com.citytemperature.domain.contract.CityTemperature;
import com.citytemperature.service.contract.CityTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;


@RestController
@RequestMapping("/api/v1/cities")
@Validated
public class CityTemperatureController {

    private final CityTemperatureService cityTemperatureService;
    private static final String CITY_NAME_VALIDATION = "'cityName' must have at most 85 characters.";

    @Autowired
    public CityTemperatureController(@Qualifier("MetaWeatherCityTemperatureService") CityTemperatureService cityTemperatureService) {
        this.cityTemperatureService = cityTemperatureService;
    }

    @GetMapping("/{cityName}/temperature")
    public ResponseEntity<CityTemperature> getCityTemperature(@PathVariable("cityName") @Size(max = 85, message = CITY_NAME_VALIDATION) final String cityName) {
        return ResponseEntity.ok().body(cityTemperatureService.findCityTemperature(cityName));
    }
}
