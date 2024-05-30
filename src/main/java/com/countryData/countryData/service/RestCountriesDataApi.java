package com.countryData.countryData.service;

import com.countryData.countryData.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class RestCountriesDataApi {

    private final RestTemplate restTemplate;

    @Autowired
    public RestCountriesDataApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Country> getAllCountries() {
        var REST_COUNTRIES_API_URL = "https://restcountries.com/v3.1/";
        String url = REST_COUNTRIES_API_URL + "all";
        try {
            ResponseEntity<Country[]> responseEntity = restTemplate.getForEntity(url, Country[].class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                Country[] countries = responseEntity.getBody();
                return Arrays.asList(Objects.requireNonNull(countries));
            } else {
                return Collections.emptyList();
            }
        } catch (HttpClientErrorException ex) {
            // Handle HTTP client errors
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }


}
