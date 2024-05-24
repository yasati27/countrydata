package com.countryData.countryData;

import com.countryData.countryData.entity.Country;
import com.countryData.countryData.service.RestCountriesApiClient;
import com.countryData.countryData.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class CountryDataApplicationTests {

	@Mock
	private RestCountriesApiClient restCountriesApiClient;

	@InjectMocks
	private CountryService countryService;
/*
    @Autowired
	private Country countries;*/

	//List<String> mockborder ;

	List<Country> mockCountries = new ArrayList<>();
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		 mockCountries =Arrays.asList(
				new Country("Netherlands", 17700000, 41850, Arrays.asList("Belgium", "Germany"), "Europe"),
				//		new Country("France", 67000000, 551695, Arrays.asList("Spain", "Germany", "Italy"), "Europe"),
				new Country("USA", 331000000, 9833517, Arrays.asList("Canada", "Mexico"), "North America"),
				//		new Country("India", 1380000000, 3287263, Arrays.asList("China", "Pakistan"), "Asia"),
				//		new Country("Japan", 126000000, 377975, Arrays.asList("South Korea", "China"), "Asia"),
				//	new Country("Nepal", 29600000, 147516, Arrays.asList("China", "India"), "Asia"),
				new Country("Turkey", 83100000, 783562, Arrays.asList("Greece", "Syria"), "Asia"),
				new Country("Iran", 84000000, 1648195, Arrays.asList("Iraq", "Afghanistan"), "Asia")

		);
	}




	@Test
	public void test_getCountriesByPopulationDensity(){


		when(restCountriesApiClient.getAllCountries()).thenReturn(mockCountries);

		// Act
		List<Country> result;
        result = countryService.getCountriesByPopulationDensity();

        // Assert
		assertEquals(mockCountries, result);	}
}
