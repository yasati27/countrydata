package com.countryData.countryData.service;

import com.countryData.countryData.controller.CountryController;
import com.countryData.countryData.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    Logger logger = LoggerFactory.getLogger(CountryController.class);

    private final RestCountriesDataApi restCountriesDataApi;

    public CountryServiceImpl(RestCountriesDataApi restCountriesDataApi) {
        this.restCountriesDataApi = restCountriesDataApi;
    }

    public List<Country> getCountriesByPopulationDensity() {
        List<Country> countries = restCountriesDataApi.getAllCountries();

        if (countries.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No countries found.");
        }
        try {
            return countries.stream()
                    .sorted(Comparator.comparingDouble(Country::getPopulationDensity).reversed())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.info("Countries not found");
        }
        return null;
    }

    public Country getAsianCountryWithMostBorderingCountries() {
        List<Country> countries = restCountriesDataApi.getAllCountries();
        if (countries.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No countries found.");
        }
        // get list of all asian countries
        List<Country> asianCountries = countries.stream()
                .filter(c -> c.getRegion().equals("Asia")).toList();

        // Collect all asian country code
        List<String> asianCountryCodes = asianCountries.stream()
                .map(Country::getCountryCode)
                .toList();


        //Get all asian countries as key and total number of non regional(asia) border as value
        Map<String, Integer> asianCountriesWithBorders = asianCountries.stream()
                .filter(c -> c.getBorders() != null)
                .collect(Collectors.toMap(
                        c -> c.getName().getCommon(),
                        c -> (int) c.getBorders().stream().filter(e -> !asianCountryCodes.contains(e)).count()
                ));


        String countryName = asianCountriesWithBorders.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        logger.info("Asian country with maximum non asian borders is {}", countryName);

        try {
            return countries
                    .stream()
                    .filter(c -> c.getName().getCommon().equalsIgnoreCase(countryName.trim()))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            logger.warn("No asian countries returned");
        }
        return null;

    }


}



