package com.countryData.countryData;

import com.countryData.countryData.controller.CountryController;
import com.countryData.countryData.controller.CountryNotFoundException;
import com.countryData.countryData.entity.Country;
import com.countryData.countryData.entity.Name;
import com.countryData.countryData.service.CountryService;
import com.countryData.countryData.service.RestCountriesDataApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ch.qos.logback.core.util.AggregationType.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class CountyControllerTests {

    @Mock
    private RestCountriesDataApi restCountriesDataApi;


    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getCountriesByPopulationDensity() {
        // Mocking data
        List<Country> mockCountries = Arrays.asList(new Country(new Name("France", "France"), 67000000, 551695, Arrays.asList("SPA", "GER", "ITA"), "Europe", "FRA"), new Country(new Name("NA", "NA"), 331000000, 9833517, Arrays.asList("CAN", "MEX"), "America", "NAA"));

        // Mocking behavior
        when(countryService.getCountriesByPopulationDensity()).thenReturn(mockCountries);

        // Testing the controller method
        ResponseEntity<List<Country>> responseEntity = countryController.getCountriesByPopulationDensity();

        // Assertions
        assertSame(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockCountries, responseEntity.getBody());
    }


    @Test
    public void test_getCountriesByPopulationDensity_noCountries() {

        // Mocking data
        when(countryService.getCountriesByPopulationDensity()).thenReturn(Collections.emptyList());

        // Assertions
        assertThrows(CountryNotFoundException.class, () -> countryController.getCountriesByPopulationDensity());
    }

    @Test
    public void test_getAsianCountryWithMostBorderingCountries() {

        // Mocking data
        Country mockCountry = new Country(new Name("Turkey", "Turkey"), 83100000, 783562, Arrays.asList("GRE", "SYR"), "Asia", "TUR");

        // Mocking behavior
        when(countryService.getAsianCountryWithMostBorderingCountries()).thenReturn(mockCountry);

        // Testing the controller method
        ResponseEntity<Country> responseEntity = countryController.getAsianCountryWithMostBorderingCountries();

        // Assertions
        assertSame(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(mockCountry, responseEntity.getBody());
    }

    @Test
    public void test_getAsianCountryWithMostBorderingCountries_noCountries() {
        // Mock no countries
        when(countryService.getAsianCountryWithMostBorderingCountries()).thenReturn(null);

        // Assertions
        assertThrows(CountryNotFoundException.class, () -> countryController.getCountriesByPopulationDensity());
    }
}

