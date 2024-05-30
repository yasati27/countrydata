# Country Data Application
This project provides functionality to retrieve and manipulate data about countries using the RestCountries API.

## Pre requisites
- \>= Java 8

## Setup Guide

1.Clone the repo with url : https://github.com/yasati27/countryData.git


2.Go to root directory that is ~/countryData and run the following command
```
  ./mvnw spring-boot:run
```
If Step 1 and 2 are properly configured, you will see **Tomcat started on port(s): 8080**.

4. Now open your localhost and choose basic auth option in Authrization in Postman. Then enter username and password. Use below Username and Password mentioned in application.properties.
  ```
  #spring security credentials
  spring.security.user.name=root
  spring.security.user.password=root
  ```
5. Now use the Postman collection mentioned at bottom to explore the APIs.

## Project Architecture
1. MVC pattern is used to seggregate the functionality,and the view part is not being used for now.
2. Outline of Core logic folder is as follows :
  ```
  -- recipebook
    |-- CountryDataApplication.java
    |-- controller
    |   |-- CountryController.java
    |-- model
    |   |-- Country.java
    |   |-- Name.java
    |-- service
        |-- CountryService.java
        |-- CountryServiceServiceImpl.java
        |-- RestCountriesDataApi
  ```
## Functionality

### RestCountriesDataApi

This component interacts with the RestCountries API to fetch country data. It provides the following functionality:

- `getAllCountries()`: Retrieves data for all countries from the RestCountries API `https://restcountries.com/v3.1/all`.

### CountryServiceImpl

This service layer provides business logic for manipulating country data. It utilizes the `RestCountriesDataApi` to fetch country data and implements the following functionality:

- `getCountriesByPopulationDensity()`: Returns a list of countries sorted by population density in descending order.
- `getAsianCountryWithMostBorderingCountries()`: Retrieves the Asian country with the most neighboring countries.

### CountryController

This controller exposes REST endpoints to interact with the country data service. It provides the following endpoints:

- `GET /countries/populationdensity`: Retrieves the sorted list of countries by population density in descending order.
- `GET /countries/most-bordering-country`: Retrieves country in Asia containing the most bordering countries of a different region.

### ScreenShots for response for both the above get apis:
- GET /countries/populationdensity :
 ![Screenshot (164)](https://github.com/yasati27/countryData/assets/170762397/e06dafa7-fe1f-4798-904f-1dbd0b34a5dc)

- GET /countries/most-bordering-country:
 ![Screenshot (167)](https://github.com/yasati27/countryData/assets/170762397/c8406815-0b4e-4306-a7b2-f6c5f265e38d)

## Testing

The project includes unit tests to ensure the functionality of the service layer. These tests validate the behavior of the `CountryServiceImpl` class under various scenarios.

## Dependencies

- Java
- Spring Boot
- Spring Web
- Spring Test
- Mockito
- SLF4J

## Contributors

- Yashasavi Asati
- asatiyashasavi@gmail.com


