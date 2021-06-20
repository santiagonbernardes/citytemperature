package com.citytemperature.service.impl;

import com.citytemperature.config.WebClientConfig;
import com.citytemperature.domain.contract.CityTemperature;
import com.citytemperature.domain.contract.Woeid;
import com.citytemperature.domain.impl.MetaWeatherWoeidImpl;
import com.citytemperature.exceptions.CityNotFoundException;
import com.citytemperature.exceptions.CityTemperatureNotFoundException;
import com.citytemperature.exceptions.MetaWeatherIntegrationException;
import com.citytemperature.helpers.MockResponseHelper;
import com.citytemperature.responses.MetaWeatherCityTemperatureResponseMock;
import com.citytemperature.responses.MetaWeatherConsolidatedWeatherMock;
import com.citytemperature.service.contract.CityTemperatureService;
import com.citytemperature.service.contract.WoeidService;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MetaWeatherCityTemperatureServiceImplTest {

    private MockWebServer mockServer;

    @Mock
    private WoeidService woeidServiceMock;
    private CityTemperatureService underTest;
    private MockResponseHelper helper;


    @BeforeEach
    void setupTestsVariables() throws IOException {
        this.mockServer = new MockWebServer();
        this.mockServer.start();
        final String baseUrl = String.format("http://%s:%s", mockServer.getHostName(), mockServer.getPort());
        underTest = new MetaWeatherCityTemperatureServiceImpl(this.woeidServiceMock, WebClientConfig.buildWebClient(baseUrl, new ObjectMapper()));
        this.helper = new MockResponseHelper();

        // sets a default behavior for this service, so I don't need to write it every time.
        final List<Woeid> defaultReturn = Collections.singletonList(new MetaWeatherWoeidImpl(484848, "City"));
        when(this.woeidServiceMock.findAllWoeidByCitiesName(any())).thenReturn(defaultReturn);
    }

    @AfterEach
    void shutdownServer() throws IOException {
        this.mockServer.shutdown();
    }

    @Test
    void shouldThrowCityNotFoundExceptionWhenWoeidServiceDoesntReturnAnyWoeid() {
        when(this.woeidServiceMock.findAllWoeidByCitiesName(any())).thenReturn(Collections.emptyList());
        assertThrows(CityNotFoundException.class,
                () -> this.underTest.findCityTemperature("Any"));
    }

    @Test
    void shouldTakeTheFirstWoeidReturnedByWoeidServiceAndUseItsIdToSearchForTemperatures() {
        final MetaWeatherWoeidImpl firstInList = spy(new MetaWeatherWoeidImpl(1, "City"));
        final MetaWeatherWoeidImpl secondInList = spy(new MetaWeatherWoeidImpl(2, "Any"));
        final List<Woeid> returnedWoeid = Arrays.asList(firstInList, secondInList);
        when(this.woeidServiceMock.findAllWoeidByCitiesName(any())).thenReturn(returnedWoeid);
        final MetaWeatherConsolidatedWeatherMock returned = new MetaWeatherConsolidatedWeatherMock(LocalDate.now(), BigDecimal.valueOf(27.8));
        this.mockServer.enqueue(helper.getMockResponseStatusCode200(new MetaWeatherCityTemperatureResponseMock(Collections.singletonList(returned), "Test")));
        this.underTest.findCityTemperature("Any");
        verify(firstInList, times(1)).getId();
        verify(secondInList, never()).getId();
    }

    @Test
    void shouldReturnOldestTheCityTemperatureGivenItsApplicableDate() {
        final MetaWeatherConsolidatedWeatherMock expected = new MetaWeatherConsolidatedWeatherMock(LocalDate.now(), BigDecimal.valueOf(27.8));
        final List<MetaWeatherConsolidatedWeatherMock> returnedTemperature = Arrays.asList(
                new MetaWeatherConsolidatedWeatherMock(LocalDate.now().plusDays(5), BigDecimal.valueOf(30.)),
                expected,
                new MetaWeatherConsolidatedWeatherMock(LocalDate.now().plusDays(2), BigDecimal.valueOf(29.4)),
                new MetaWeatherConsolidatedWeatherMock(LocalDate.now().plusDays(3), BigDecimal.valueOf(29.7))
        );
        mockServer.enqueue(helper.getMockResponseStatusCode200(new MetaWeatherCityTemperatureResponseMock(returnedTemperature, "Test")));
        final CityTemperature actual = underTest.findCityTemperature("Any");
        assertEquals(expected.getApplicableDate(), actual.getDateThisTemperatureIsExpected());
        assertEquals(0, expected.getTheTemp().compareTo(actual.getTemperatureInCelsius()));
    }

    @Test
    void shouldRaiseCityTemperatureNotFoundExceptionWhenNoConsolidatedWeatherIsFound() {
        mockServer.enqueue(helper.getMockResponseStatusCode200(new MetaWeatherCityTemperatureResponseMock(Collections.emptyList(), "Test")));
        assertThrows(CityTemperatureNotFoundException.class,
                () -> this.underTest.findCityTemperature("Any"));
    }

    @Test
    void shouldRaiseCityTemperatureNotFoundExceptionWhenConsolidatedWeatherIsNull() {
        mockServer.enqueue(helper.getMockResponseStatusCode200(new MetaWeatherCityTemperatureResponseMock(null, "Test")));
        assertThrows(CityTemperatureNotFoundException.class,
                () -> this.underTest.findCityTemperature("Any"));
    }

    @Test
    void shouldRaiseCityTemperatureNotFoundExceptionWhenResponseBodyIsNull() {
        mockServer.enqueue(helper.getMockResponseStatusCode200(null));
        assertThrows(CityTemperatureNotFoundException.class,
                () -> this.underTest.findCityTemperature("Any"));
    }

    @Test
    void shouldRaiseCityTemperatureNotFoundExceptionWhenMetaWeatherReturnsClientErrorStatusCode() {
        // Im returning a valid body so I don't get fooled and the exception is raised because the response is null.
        mockServer.enqueue(helper.getMockResponseWithStatusCodeAndBody(404, new MetaWeatherCityTemperatureResponseMock(Collections.emptyList(), "Test")));
        assertThrows(CityTemperatureNotFoundException.class,
                () -> this.underTest.findCityTemperature("Any"));
    }

    @Test
    void shouldRaiseCityMetaWeatherIntegrationExceptionWhenMetaWeatherReturnsServerErrorStatusCode() {
        mockServer.enqueue(helper.getMockResponseWithStatusCodeAndBody(500, null));
        assertThrows(MetaWeatherIntegrationException.class,
                () -> this.underTest.findCityTemperature("Any"));
    }
}