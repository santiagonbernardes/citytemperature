package com.citytemperature.service.impl;

import com.citytemperature.builders.MetaWeatherWoeidResponseBuilder;
import com.citytemperature.domain.contract.Woeid;
import com.citytemperature.exceptions.MetaWeatherIntegrationException;
import com.citytemperature.helpers.MockResponseHelper;
import com.citytemperature.helpers.TestWebClientHelper;
import com.citytemperature.responses.MetaWeatherWoeidResponse;
import com.citytemperature.service.contract.WoeidService;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MetaWeatherWoeidServiceImplTest {

    private MockWebServer mockServer;
    private WoeidService underTest;
    private MockResponseHelper helper;

    @BeforeEach
    void setupTestsVariables() throws IOException {
        this.mockServer = new MockWebServer();
        this.mockServer.start();
        underTest = new MetaWeatherWoeidServiceImpl(TestWebClientHelper.buildTestWebClient(this.mockServer.getHostName(), this.mockServer.getPort()));
        this.helper = new MockResponseHelper();
    }

    @AfterEach
    void shutdownServer() throws IOException {
        this.mockServer.shutdown();
    }

    @Test
    void shouldReturnAListOfASingleWoeidWhenOnlyOneWoeidIsReturnedByBackendApi() {
        final MetaWeatherWoeidResponse serverResponseBody = MetaWeatherWoeidResponseBuilder.builder()
                .withTitle("San Francisco")
                .withLocationType("City")
                .withWoeid(2487956)
                .withLatLong("37.777119, -122.41964")
                .build();
        this.mockServer.enqueue(this.helper.getMockResponseStatusCode200(Collections.singletonList(serverResponseBody)));
        final List<Woeid> citiesWoeid = this.underTest.findAllWoeidByCitiesName("Any city name.");
        assertEquals(1, citiesWoeid.size());
        final Woeid foundWoeid = citiesWoeid.get(0);
        assertEquals(serverResponseBody.getWoeid(), foundWoeid.getId());
        assertEquals(serverResponseBody.getLocationType(), foundWoeid.getType());
    }

    @Test
    void shouldExistOnlyObjectsOfLocationTypeCityInTheReturnedListIgnoringAnyOtherType() {
        final MetaWeatherWoeidResponse cityLocationType = MetaWeatherWoeidResponseBuilder.builder()
                .withDefaultDataAndLocationTypeCity()
                .build();
        final MetaWeatherWoeidResponse otherLocationType = MetaWeatherWoeidResponseBuilder.builder()
                .withDefaultData()
                .build();
        this.mockServer.enqueue(this.helper.getMockResponseStatusCode200(Arrays.asList(cityLocationType, otherLocationType)));
        final List<Woeid> citiesWoeid = this.underTest.findAllWoeidByCitiesName("?");
        assertEquals(1, citiesWoeid.size());
    }

    @Test
    void shouldExistOnlyObjectsOfLocationTypeCityInTheReturnedListIgnoringAnyOtherTypeRegardlessOfLetterCase() {
        final MetaWeatherWoeidResponse upperCaseCityLocationType = MetaWeatherWoeidResponseBuilder.builder()
                .withDefaultData()
                .withLocationType("CITY")
                .build();
        final MetaWeatherWoeidResponse lowerCaseCityLocationType = MetaWeatherWoeidResponseBuilder.builder()
                .withDefaultData()
                .withLocationType("city")
                .build();
        final MetaWeatherWoeidResponse mixedCaseCityLocationType = MetaWeatherWoeidResponseBuilder.builder()
                .withDefaultData()
                .withLocationType("CiTy")
                .build();
        final MetaWeatherWoeidResponse otherLocationType = MetaWeatherWoeidResponseBuilder.builder()
                .withDefaultData()
                .build();
        this.mockServer.enqueue(this.helper.getMockResponseStatusCode200(
                Arrays.asList(upperCaseCityLocationType, lowerCaseCityLocationType, mixedCaseCityLocationType, otherLocationType)
        ));
        final List<Woeid> citiesWoeid = this.underTest.findAllWoeidByCitiesName("?");
        assertEquals(3, citiesWoeid.size());
    }

    @Test
    void shouldReturnAnEmptyListWhenNoObjectIsReturnedByBackendApi() {
        this.mockServer.enqueue(this.helper.getMockResponseStatusCode200(Collections.emptyList()));
        assertEquals(0, this.underTest.findAllWoeidByCitiesName("Any city name.").size());
    }

    @Test
    void shouldReturnAListContainingAllObjectsReturnedByTheBackendApi() {
        final List<Integer> expectedIds = Arrays.asList(454578, 123456789, 30405060, 4, 102030405, 70109);
        final List<MetaWeatherWoeidResponse> serverResponseBody = expectedIds.stream()
                .map(id -> MetaWeatherWoeidResponseBuilder.builder()
                        .withDefaultDataAndLocationTypeCity()
                        .withWoeid(id)
                        .build()
                ).collect(Collectors.toList());
        this.mockServer.enqueue(this.helper.getMockResponseStatusCode200(serverResponseBody));
        final List<Woeid> citiesWoeid = this.underTest.findAllWoeidByCitiesName("Any.");

        assertEquals(6, citiesWoeid.size());
        assertEquals(expectedIds.get(0), citiesWoeid.get(0).getId());
        assertEquals(expectedIds.get(1), citiesWoeid.get(1).getId());
        assertEquals(expectedIds.get(2), citiesWoeid.get(2).getId());
        assertEquals(expectedIds.get(3), citiesWoeid.get(3).getId());
        assertEquals(expectedIds.get(4), citiesWoeid.get(4).getId());
        assertEquals(expectedIds.get(5), citiesWoeid.get(5).getId());
    }

    @Test
    void shouldReturnEmptyListIfWebClientReturnsNull() {
        this.mockServer.enqueue(helper.getMockResponseStatusCode200(null));
        final List<Woeid> citiesWoeid = this.underTest.findAllWoeidByCitiesName("Any.");
        assertTrue(citiesWoeid.isEmpty());
    }

    @ParameterizedTest(name = "MetaWeather returns {0}.")
    @ValueSource(ints = {400, 404, 500, 503})
    void shouldThrowMetaWeatherIntegrationExceptionWhenMetaWeatherReturnsClientOrServerErrorStatusCode(final int statusCode) {
        this.mockServer.enqueue(helper.getMockResponseWithStatusCodeAndBody(statusCode, null));
        assertThrows(MetaWeatherIntegrationException.class,
                () -> this.underTest.findAllWoeidByCitiesName("Any."));
    }
}