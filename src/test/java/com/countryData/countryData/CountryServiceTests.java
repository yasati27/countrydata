package com.countryData.countryData;

import com.countryData.countryData.entity.Country;
import com.countryData.countryData.entity.Name;
import com.countryData.countryData.service.CountryServiceImpl;
import com.countryData.countryData.service.RestCountriesDataApi;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CountryServiceTests {

    @Mock
    private RestCountriesDataApi restCountriesDataApi;


    @InjectMocks
    private CountryServiceImpl countryService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void test_getCountriesByPopulationDensity() {
        List<Country> mockCountries = Arrays.asList(
                new Country(new Name("France", "France"), 67000000, 551695, Arrays.asList("SPA", "GER", "ITA"), "Europe", "FRA"),
                new Country(new Name("NA", "NA"), 331000000, 9833517, Arrays.asList("CAN", "MEX"), "America", "NAA")
        );

        // Mock behavior
        when(restCountriesDataApi.getAllCountries()).thenReturn(mockCountries);

        // Test
        List<Country> result = countryService.getCountriesByPopulationDensity();

        //Assertions
        assertEquals(mockCountries, result);
    }

    @Test
    public void test_getCountriesByPopulationDensity_noCountries() {

        // Mock no countries
        when(restCountriesDataApi.getAllCountries()).thenReturn(Collections.emptyList());

        //Assertions
        assertThrows(ResponseStatusException.class, () -> countryService.getCountriesByPopulationDensity());
    }


    @Test
    public void test_getAsianCountryWithMostBorderingCountries() {
        List<Country> mockCountries = Arrays.asList(
                new Country(new Name("Japan", "Japan"), 126000000, 377975, Arrays.asList("TUR", "CHI"), "Asia", "JAP"),
                new Country(new Name("Turkey", "Turkey"), 83100000, 783562, Arrays.asList("GRE", "SYR"), "Asia", "TUR"),
                new Country(new Name("France", "France"), 67000000, 551695, Arrays.asList("SPA", "GER", "ITA"), "Europe", "FRA"),
                new Country(new Name("NA", "NA"), 331000000, 9833517, Arrays.asList("CAN", "MEX"), "America", "NAA"),
                new Country(new Name("Nepal", "Nepal"), 29600000, 147516, Arrays.asList("TUR", "JAP"), "Asia", "NEP")
                // Country codes JAP TUR NEP
        );

        Country expected = new Country(new Name("Turkey", "Turkey"), 83100000, 783562, Arrays.asList("GRE", "SYR"), "Asia", "TUR");

        // Mock behavior
        when(restCountriesDataApi.getAllCountries()).thenReturn(mockCountries);

        // Test
        Country result = countryService.getAsianCountryWithMostBorderingCountries();

        //Assertions
        assertEquals(expected, result);
    }

    @Test
    public void test_getAsianCountryWithMostBorderingCountries_noCountries() {

        // Mock no countries
        when(restCountriesDataApi.getAllCountries()).thenReturn(Collections.emptyList());

        //Assertions
        assertThrows(ResponseStatusException.class, () -> countryService.getAsianCountryWithMostBorderingCountries());
    }

}
