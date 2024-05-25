package com.countryData.countryData;

import com.countryData.countryData.entity.Country;
import com.countryData.countryData.entity.Name;
import com.countryData.countryData.service.RestCountriesApiClient;
import com.countryData.countryData.service.CountryService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CountryDataApplicationTests {

	@Mock
	private RestCountriesApiClient restCountriesApiClient;

	@InjectMocks
	private CountryService countryService;



	@Before
	public void setUp() {
		//MockitoAnnotations.initMocks(this); // Initialize Mockito annotations

	}

//	List<Country> mockCountries = new ArrayList<>();





	@Test
	public void test_getCountriesByPopulationDensity() {
		List<Country> mockCountries = Arrays.asList(
				new Country(new Name("France", "France"), 67000000, 551695, Arrays.asList("SPA", "GER", "ITA"), "Europe", "FRA"),
				new Country(new Name("NA", "NA"), 331000000, 9833517, Arrays.asList("CAN", "MEX"), "America", "NAA")
/*				new Country("India", 1380000000, 3287263, Arrays.asList("China", "Pakistan"), "Asia"),
				new Country("Japan", 126000000, 377975, Arrays.asList("South Korea", "China"), "Asia"),
				new Country("Nepal", 29600000, 147516, Arrays.asList("China", "India"), "Asia"),
				new Country("Turkey", 83100000, 783562, Arrays.asList("Greece", "Syria"), "Asia"),
				new Country("Iran", 84000000, 1648195, Arrays.asList("Iraq", "Afghanistan"), "Asia")*/
		);

		// Mock behavior
		when(restCountriesApiClient.getAllCountries()).thenReturn(mockCountries);

		// Test
		List<Country> result = countryService.getCountriesByPopulationDensity();
		assertEquals(mockCountries, result);
	}

	@Test
	public void test_getCountriesByPopulationDensity_noCountries() {
		// Mock no countries
		when(restCountriesApiClient.getAllCountries()).thenReturn(Collections.emptyList());

		// Test
		countryService.getCountriesByPopulationDensity();
	}
}
