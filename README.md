# Country Data Application
This project provides functionality to retrieve and manipulate data about countries using the RestCountries API.

## Pre requisites
- \>= Java 8
- \>= MySQL(5.6)
- Create database with name taskdb

## Setup Guide
1. Login to your mysql terminal and create mysql database with following command : 
```
  create database taskdb;
```
2. Clone the repo with url ``` https://github.com/yasati27/countrydata.git ```
3. Change the application.properties file under DataBase Connection

  - Change the datasource url to your Database host as :   
      spring.datasource.url=jdbc:mysql://${database.host}:${database.port}/${database.name}
  - Change the \<username\> to your Mysql Username
  - Change the \<password\> to your Mysql Password
  - Example:
    ```
      #Database connection
      spring.datasource.url=jdbc:mysql://localhost:3306/taskdb
      spring.datasource.username=username
      spring.datasource.password=password
    ```

4. Go to root directory that is ~/countrydata and run the following command
```
  ./mvnw spring-boot:run
```
   Note :: If Step 1 and 2 are properly configured, you will see **Tomcat started on port(s): 9090**.
 
5. Now to access rest endpoint user must use jwt token. Below steps can be followed:

      5.1. Register user: hit api http://localhost:9090/auth/signup, select POST and enter fullName, email, password. Below json format can be followed if using postman.
```
{
    "fullName": "userx",
    "email": "userx@userx.com",
    "password": "userx"
}
```

On success request body and response body will be as shown below:
![image](https://github.com/yasati27/countrydata/assets/170762397/53512d49-34fe-4dce-bc79-02c24fba1e92)

  5.2. Login user: after succesful registration hit api http://localhost:9090/auth/login, select POST and email and password. Below json format can be followed if using postman.
```
{
    "email": "userx@userx.com",
    "password": "userx"
}
```

On success token will be generated as shown below:
![image](https://github.com/yasati27/countrydata/assets/170762397/ec0e681c-a6d7-4538-91bf-2a1d021410af)

5.3. Copy and Add Bearer Token : Now copy generated token and add in header key = Autherization and Value + Bearer {generated token} or if using postman click on Autherization and select Bearer Token from the list and paste the token as shown below:

![image](https://github.com/yasati27/countrydata/assets/170762397/6ba542d7-0244-418b-9617-42ca6ffc430c)

6. Now use the Postman collection mentioned at bottom to explore the APIs


## Project Architecture
1. MVC pattern is used to seggregate the functionality,and the view part is not being used for now.
2. Outline of Core logic folder is as follows :
  ```
  -- countryData
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

### Visual represenation of request and response for both the above get apis are shown below:
- GET /countries/populationdensity :
 ![image](https://github.com/yasati27/countrydata/assets/170762397/a8f89d0f-3879-4997-b65c-e00db2db4457)


- GET /countries/most-bordering-country:
  ![image](https://github.com/yasati27/countrydata/assets/170762397/194eb7db-2491-42c9-a8d7-f410cad9cddb)


## Testing

The project includes unit tests to ensure the functionality of the controller layer, service layer and entity layer. These tests validate the behavior of the controller, service and model class under various scenarios.

## Dependencies

- Java
- Spring Boot
- Spring Web
- Spring Test
- Spring Boot Actuator
- Mockito
- SLF4J
- Spring Data JPA
- Spring Security
- JUnit Jupiter API (JUnit 5)
- Lombok
- JJWT (JSON Web Token)

## Spring security using JWT authentication
 
- JWT Authentication: Utilizes JSON Web Tokens (JWT) for secure authentication, providing stateless authentication across HTTP requests.
- Secure API Access: JWT tokens are issued upon successful authentication and must be included in subsequent requests' Authorization headers to access protected API endpoints.

## Exception Handling

- Global Exception Handling: In this project, I've utilized @RestControllerAdvice for centralized exception handling, ensuring consistent error responses throughout the application.
- Custom Error Responses: Leveraging @RestControllerAdvice, custom exception handlers are implemented to convert exceptions into informative error messages, enhancing the robustness and user experience of the API.

## Contributors

- Yashasavi Asati
- asatiyashasavi@gmail.com


