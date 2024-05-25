package com.countryData.countryData.service;

import com.countryData.countryData.entity.Country;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final RestCountriesApiClient restCountriesApiClient;

    public CountryService(RestCountriesApiClient restCountriesApiClient) {
        this.restCountriesApiClient = restCountriesApiClient;
    }

    public List<Country> getCountriesByPopulationDensity() {
        List<Country> countries = restCountriesApiClient.getAllCountries();
        if (countries.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No countries found.");
        }
        return countries.stream()
                .sorted(Comparator.comparingDouble(Country::getPopulationDensity).reversed())
                .collect(Collectors.toList());
    }

    public Country getAsianCountryWithMostBorderingCountries() {
        List<Country> countries = restCountriesApiClient.getAllCountries();
        if (countries.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No countries found.");
        }
       List<String> asianCountryCodes = countries.stream()
               .filter(c -> c.getRegion().equals("Asia"))
               .map(Country::getCountryCode)
               .toList();

        System.out.println("ASIAN COUNTRIES CODES::" +asianCountryCodes);

        Map<String, Integer> asianCountries = countries.stream()
                .filter(c -> c.getRegion().equals("Asia"))
                .filter(c -> c.getBorders() != null)
                .collect(Collectors.toMap(
                        c -> c.getName().getCommon(),
                        c -> (int) c.getBorders().stream().filter(e -> !asianCountryCodes.contains(e)).count()
                ));
        System.out.println("ALL ASIAN COUNTRIES with max non-Asian borders: " + asianCountries);


        String str = String.valueOf(asianCountries.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey));

        System.out.println("JUST ONE COUNTRY"+str);
         return countries.stream().filter(c -> c.getName().getCommon().equals(str)).findFirst().orElse(null);


    }




}



