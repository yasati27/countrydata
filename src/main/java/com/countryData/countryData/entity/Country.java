package com.countryData.countryData.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
public class Country {

    private Name name;
    private double population;
    private double area;
    private List<String> borders;
    private String region;
    @JsonProperty("cca3")
    private String countryCode;


    // Implementing the population density calculation
    public double getPopulationDensity() {
        return population / area;
    }

}
