package com.springboot.weathercacheservice.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.weathercacheservice.entity.Forecast;
import com.springboot.weathercacheservice.exception.ForecastNotAvailableException;
import com.springboot.weathercacheservice.dto.WeatherData;
import com.springboot.weathercacheservice.dto.WeatherRequest;
import com.springboot.weathercacheservice.service.ForecastService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class ForecastResource {

    @Autowired
    ForecastService service;

    @GetMapping("/forecast/{city}")
    public ResponseEntity<WeatherData> getWeatherForecast(@Valid WeatherRequest weatherRequest) throws JsonProcessingException, ForecastNotAvailableException {
        String data = service.getWeatherData(weatherRequest.getCity());
        ObjectMapper mapper = new ObjectMapper();
        return ResponseEntity.ok(mapper.readValue(data,WeatherData.class));
    }

    @PostMapping("/forecast/{city}")
    public ResponseEntity<Forecast> updateForecastCache(@PathVariable String city, @Valid @RequestBody WeatherData data) throws JsonProcessingException {
        Forecast forecast = new Forecast();
        forecast.setCity(city);
        ObjectMapper mapper = new ObjectMapper();
        forecast.setData(mapper.writeValueAsString(data));
        return new ResponseEntity<>(service.saveWeatherData(forecast), HttpStatus.CREATED);
    }
}
