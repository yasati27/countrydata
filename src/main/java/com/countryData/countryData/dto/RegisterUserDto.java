package com.countryData.countryData.dto;

import lombok.Data;

@Data
public class RegisterUserDto {

    private String password;

    private String fullName;

    private String email;

}
