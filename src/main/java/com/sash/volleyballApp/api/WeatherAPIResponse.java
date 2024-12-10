package com.sash.volleyballApp.api;

import lombok.Data;

import java.util.List;

@Data
public class WeatherAPIResponse {

    String temperature;
    String wind;
    String description;
    List<Forecast> forecast;

    String message;

}
