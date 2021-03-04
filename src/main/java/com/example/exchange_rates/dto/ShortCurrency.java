package com.example.exchange_rates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShortCurrency {
    @JsonProperty("chCode")
    @ApiModelProperty(value = "Код валюты", example = "USD")
    String chCode;
    @JsonProperty("name")
    @ApiModelProperty(value = "Имя валюты")
    String name;
    @JsonProperty("course")
    @ApiModelProperty(value = "Курс валюты")
    double course;

    public ShortCurrency(String chCode, String name, double course) {
        this.chCode = chCode;
        this.name = name;
        this.course = course;
    }
}
