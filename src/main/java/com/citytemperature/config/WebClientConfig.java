package com.citytemperature.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient getMetaWeatherWebClient() {
        return WebClient.builder()
                .baseUrl("https://www.metaweather.com/api/location")
                .build();
    }
}
