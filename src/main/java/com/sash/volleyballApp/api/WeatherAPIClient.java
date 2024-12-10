package com.sash.volleyballApp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherAPIClient {

    @Autowired
    private RestTemplate restTemplate;

    public WeatherAPIResponse getWeather(String city) {
        try {
            ResponseEntity<WeatherAPIResponse> responseW = restTemplate.getForEntity(
                    "https://goweather.herokuapp.com/weather/" + city, WeatherAPIResponse.class);
            System.out.println(responseW);
            System.out.println(responseW.getBody());
            return responseW.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            // Handle 404 gracefully by returning a placeholder response
            System.out.println("404 Not Found: No data available for city " + city);
            WeatherAPIResponse noDataResponse = new WeatherAPIResponse();
            noDataResponse.setMessage("No data available for the specified city.");
            return noDataResponse;
        } catch (Exception e) {
            // Handle other errors gracefully
            System.out.println("Error fetching weather data: " + e.getMessage());
            WeatherAPIResponse errorResponse = new WeatherAPIResponse();
            errorResponse.setMessage("An error occurred while fetching the weather data.");
            return errorResponse;
        }
    }
}
