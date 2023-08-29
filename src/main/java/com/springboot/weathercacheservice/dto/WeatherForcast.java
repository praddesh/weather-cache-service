package com.springboot.weathercacheservice.dto;

import lombok.Data;

@Data
public class WeatherForcast {
	private String main;
	private String description;
	private String icon;
}
