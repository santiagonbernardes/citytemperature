package com.citytemperature.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient getMetaWeatherWebClient(final ObjectMapper mapper) {
    return buildWebClient("https://www.metaweather.com/api/location", mapper);
  }

  public static WebClient buildWebClient(final String baseUrl, final ObjectMapper mapper) {
    // By default Jackson can't understand LocalDateTime or ZonedDateTime. This method teaches
    // Jackson how to understand them.
    mapper.registerModule(new JavaTimeModule());
    return WebClient.builder()
        .baseUrl(baseUrl)
        // Adding the configured mapper to the webclient.
        .codecs(
            clientCodecConfigurer ->
                clientCodecConfigurer
                    .defaultCodecs()
                    .jackson2JsonDecoder(
                        new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON)))
        .codecs(
            clientCodecConfigurer ->
                clientCodecConfigurer
                    .defaultCodecs()
                    .jackson2JsonEncoder(
                        new Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON)))
        .build();
  }
}
