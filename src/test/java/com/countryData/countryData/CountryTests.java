package com.countryData.countryData;


import com.countryData.countryData.entity.Country;
import com.countryData.countryData.entity.Name;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryTests {

    //Test Constructor
    @Test
    public void test_Constructor() {

        // Mocking Data
        Name name = new Name("India", "India");
        double population = 13000000;
        double area = 3300000;
        List<String> borders = Arrays.asList("Nepal", "Srilanka","Bangladesh");
        String region = "Asia";
        String countryCode = "IND";

        Country mockCountry = new Country(name, population, area, borders, region, countryCode);

        //assert
        assertNotNull(mockCountry);
        assertEquals(name, mockCountry.getName());
        assertEquals(population, mockCountry.getPopulation());
        assertEquals(area, mockCountry.getArea());
        assertEquals(borders, mockCountry.getBorders());
        assertEquals(region, mockCountry.getRegion());
        assertEquals(countryCode, mockCountry.getCountryCode());
    }

    // Test population density method
    @Test
    public void test_getPopulationDensity() {

        Name name = new Name("India", "India");
        double population = 13000000;
        double area = 3300000;
        List<String> borders = Arrays.asList("Nepal", "Srilanka","Bangladesh");
        String region = "Asia";
        String countryCode = "IND";

        Country country = new Country(name, population, area, borders, region, countryCode);

        // Expected population density = population / area
        double expectedDensity = population / area;

        //assert
        assertEquals(expectedDensity, country.getPopulationDensity());
    }

    //Test toString method
    @Test
    public void test_toString() {
        Name name = new Name("India", "India");
        double population = 13000000;
        double area = 3300000;
        List<String> borders = Arrays.asList("Nepal", "Srilanka", "Bangladesh");
        String region = "Asia";
        String countryCode = "IND";

        Country country = new Country(name, population, area, borders, region, countryCode);

        // Expected string
        String expectedToString = "Country(name=" +name+ ", population=" + population + ", area="+ area + ", borders=" + borders +", region="+ region + ", countryCode=" +countryCode+ ")";

        // assert
        assertEquals(expectedToString, country.toString());
    }

    // Test equals and hashCode methods
    @Test
    public void test_EqualsAndHashCode() {
        Name name1 = new Name("India", "India");
        double population1 = 13000000;
        double area1 = 3300000;
        List<String> borders1 = Arrays.asList("Nepal", "Srilanka", "Bangladesh");
        String region1 = "Asia";
        String countryCode1 = "IND";

        Country country1 = new Country(name1, population1, area1, borders1, region1, countryCode1);
        Country country2 = new Country(name1, population1, area1, borders1, region1, countryCode1);

        // assert equals method
        assertEquals(country1, country2);
        // assert hashCode method
        assertEquals(country1.hashCode(), country2.hashCode());
    }

}
