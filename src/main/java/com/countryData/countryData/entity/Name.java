package com.countryData.countryData.entity;

import lombok.Data;

@Data
public class Name {


    private String common;
    private String official;


    public Name(String common, String official) {
        this.common = common;
        this.official= official;
    }
}
