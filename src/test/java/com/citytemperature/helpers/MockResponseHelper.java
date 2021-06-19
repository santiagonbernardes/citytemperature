package com.citytemperature.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.mockwebserver.MockResponse;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.fail;

public class MockResponseHelper {

    private final ObjectMapper mapper;

    public MockResponseHelper() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    public MockResponse getMockResponseStatusCode200(final Object responseBody) {
        return this.build(200, responseBody);
    }

    public MockResponse getMockResponseWithStatusCodeAndBody(final Integer statusCode, Object responseBody) {
        return this.build(statusCode, responseBody);
    }

    private MockResponse build(final Integer statusCode, Object responseBody) {
        try {
            return new MockResponse()
                    .setResponseCode(statusCode)
                    .setBody(this.mapper.writeValueAsString(responseBody))
                    .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        } catch (JsonProcessingException e) {
            fail("An error has occurred while parsing a MockResponse body.");
            e.printStackTrace();
            throw new IllegalArgumentException("MockResponseHelper couldn't parse responseBody");
        }
    }
}
