package com.countryData.countryData.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class Country {

    private Name name;
    private double population;
    private double area;
    private List<String> borders;
    private String region;
    @JsonProperty("cca3")
    private String countryCode;

    // Writing constructor

   // Implementing the population density calculation
    public double getPopulationDensity() {
        return population / area;
    }

}
