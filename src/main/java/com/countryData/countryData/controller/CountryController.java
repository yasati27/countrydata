package com.countryData.countryData.controller;

import com.countryData.countryData.entity.Country;
import com.countryData.countryData.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
@Slf4j
public class CountryController {

    private final CountryService countryService;


    @GetMapping("/populationdensity")
    public ResponseEntity<List<Country>> getCountriesByPopulationDensity() throws CountryNotFoundException {
        List<Country> countries = countryService.getCountriesByPopulationDensity();
        if (!countries.isEmpty()) {
            return new ResponseEntity<>(countries, HttpStatus.OK);
        } else {
            log.warn("Countries not found for populationdensity filter");
            throw new CountryNotFoundException("Countries not found for populationdensity filter");
        }
    }

    @GetMapping("/most-bordering-country")
    public ResponseEntity<Country> getAsianCountryWithMostBorderingCountries() throws CountryNotFoundException {
        Country country = countryService.getAsianCountryWithMostBorderingCountries();
        if (country != null) {
            return new ResponseEntity<>(country, HttpStatus.OK);
        } else {
            log.warn("Country not found for most-bordering-country filter");
            throw new CountryNotFoundException("Country not found for most-bordering-country filter");
        }
    }
}

