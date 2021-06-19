package com.citytemperature.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.WebClient;

public class TestWebClientHelper {

    public static WebClient buildTestWebClient(final String hostname, final Integer port) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        return WebClient.builder()
                .baseUrl(String.format("http://%s:%s", hostname, port))
                .codecs(clientCodecConfigurer -> clientCodecConfigurer
                        .defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON)))
                .codecs(clientCodecConfigurer -> clientCodecConfigurer
                        .defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON)))
                .build();
    }
}
