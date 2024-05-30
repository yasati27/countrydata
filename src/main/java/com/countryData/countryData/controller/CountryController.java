package com.countryData.countryData.controller;

import com.countryData.countryData.entity.Country;
import com.countryData.countryData.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    Logger logger = LoggerFactory.getLogger(CountryController.class);

    @GetMapping("/populationdensity")
    public ResponseEntity<List<Country>> getCountriesByPopulationDensity() {
        List<Country> countries = countryService.getCountriesByPopulationDensity();
        try {
            return new ResponseEntity<>(countries, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Countries not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/most-bordering-country")
    public ResponseEntity<Country> getAsianCountryWithMostBorderingCountries() {
        Country country = countryService.getAsianCountryWithMostBorderingCountries();
        try {
            return new ResponseEntity<>(country, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Country not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

