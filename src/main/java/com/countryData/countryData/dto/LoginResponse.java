package com.countryData.countryData.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
public class LoginResponse {
    private String token;

    private long expiresIn;


}