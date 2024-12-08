package com.sash.volleyballApp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class WeatherAPIClient {

    @Autowired
    private RestTemplate restTemplate;

    public WeatherAPIResponse getWeather(String city){

        ResponseEntity<WeatherAPIResponse> responseW = restTemplate.getForEntity("https://goweather.herokuapp.com/weather/" + city, WeatherAPIResponse.class);
        System.out.println(responseW);
        System.out.println(responseW.getBody());

        return responseW.getBody();

    }

}
