package com.countryData.countryData.service;

import com.countryData.countryData.entity.Country;

import java.util.List;

public interface CountryService {

    List<Country> getCountriesByPopulationDensity();

    Country getAsianCountryWithMostBorderingCountries();
}

