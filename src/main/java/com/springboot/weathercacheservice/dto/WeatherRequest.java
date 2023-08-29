package com.springboot.weathercacheservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WeatherRequest {
	@NotEmpty(message="City Name should not be empty")
	@Size(min=2,message="City Name should have at least two characters")
	private String city;
}
